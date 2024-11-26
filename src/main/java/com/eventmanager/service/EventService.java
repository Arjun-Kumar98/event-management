package com.eventmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import com.eventmanager.exception.*;
import java.util.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventService {
	private static final Logger logger = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventListRepository eventListRepository;
	
	@Autowired
	private PasswordService passwordService;

	@Autowired
	private LoginRepository loginRepository;

	private <T> T fetchEntityById(Optional<T> optionalEntity, String entityName) {
		return optionalEntity.orElseThrow(() -> new EntityNotFoundException(entityName + "not found"));
	}

	private void checkLogin(Integer eventManagerId) {
		if (!loginRepository.findByEventManagerIdEventManagerId(eventManagerId).isPresent()) {
			 throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Event manager has not logged in");
		}
	}

	public EventManagerEntity saveEventManagerDetails(EventManagerEntity eventEntity) {
		String hashedPassword = passwordService.hashPassword(eventEntity.getPassword());
		eventEntity.setPassword(hashedPassword);
		return eventRepository.save(eventEntity);
	}

	public String createEvent(EventListEntity eventListEntity) {

		Optional<EventManagerEntity> eventManager = eventRepository.findById(eventListEntity.getEventManagerId());
		checkLogin(eventListEntity.getEventManagerId());

		eventListRepository.save(eventListEntity);
		return "The event has been created successfully";

	}

	public EventListEntity updateEventDetails(EventListEntity eventListEntity) {
		EventListEntity existingEvent = fetchEntityById(eventListRepository.findById(eventListEntity.getEventId()),"Event");
		existingEvent.setVenue(eventListEntity.getVenue());
		existingEvent.setEventTime(eventListEntity.getEventTime());
		existingEvent.setTotalTickets(eventListEntity.getTotalTickets());
		return eventListRepository.save(eventListEntity);
	}

	public List<EventListEntity> viewEvents() {
		return eventListRepository.findAll();
	}

	public void deleteEventDetails(Integer eventId) {
		if (eventRepository.existsById(eventId)) {
			eventListRepository.deleteById(eventId);
		} else {
			 throw new EventNotFoundException("Event with ID " + eventId + " is not present");
		}
	}

	public List<EventListEntity> getEventsbetweenrange(LocalDate startdate, LocalDate enddate) {
		Date startDate = java.sql.Date.valueOf(startdate); // Convert LocalDate to java.util.Date
		Date endDate = java.sql.Date.valueOf(enddate);
		return eventListRepository.findByEventTimeBetween(startDate, endDate);
	}

	public List<EventManagerEntity> geteventManagerdetails() {
		return eventRepository.findAll();
	}

	public List<EventListEntity> getEventsByNameOrVenue(String Name, String venue) {
		return eventListRepository.findByEventNameContainingIgnoreCaseOrVenueContainingIgnoreCase(Name, venue);
	}

	public List<EventListEntity> getByEventCategory(String eventCategory) {
		return eventListRepository.findByeventCategory(eventCategory);
	}

	public String eventManagerLogin(String username, String password) {

		Optional<EventManagerEntity> manager = eventRepository.findByUsername(username);
		if(manager.isPresent() && passwordService.verifyPassword(password, manager.get().getPassword())) {
		EventManagerEntity eventManager = manager.get();
			LoginRecordEntity loginEntity = new LoginRecordEntity();
		loginEntity.setEventManagerId(eventManager.getEventManagerId());
		loginRepository.save(loginEntity);
		return "The user has successfully logged in";
		}else {
			return "incorrect username or password";
		}
	}
}
