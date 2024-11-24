package com.eventmanager.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import java.util.*;

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
	private LoginRepository loginRepository;
	public EventManagerEntity saveEventManagerDetails(EventManagerEntity eventEntity) {
		return eventRepository.save(eventEntity);
	}
	
	public EventListEntity saveEventDetails(EventListEntity eventListEntity) {
		
		
	    Optional<EventManagerEntity> event = eventRepository.findById(eventListEntity.getEventManagerId());
	    if(event.isPresent()) {

				return eventListRepository.save(eventListEntity);
	    	
	}else {
		throw new RuntimeException("Event manager is not present");
	}
	}
	
	public EventListEntity updateEventDetails(EventListEntity eventListEntity) {
		Optional<EventListEntity> eventId = eventListRepository.findById(eventListEntity.getEventId());
		if(eventId.isPresent()) {
			EventListEntity existingEvent = eventId.get();
			existingEvent.setVenue(eventListEntity.getVenue());
			existingEvent.setEventTime(eventListEntity.getEventTime());
			existingEvent.setTotalTickets(eventListEntity.getTotalTickets());
	     return eventListRepository.save(eventListEntity);
		}else {
			throw new RuntimeException("Event Id is not present");
		}
			
			
		}
	
	public List<EventListEntity> viewEventDetails(){
		return eventListRepository.findAll();
	}
	public void deleteEventDetails(Integer eventId) {
		if(eventRepository.existsById(eventId)) {
	  eventListRepository.deleteById(eventId);
		}else {
			throw new RuntimeException("Event is not present");
		}
	}
	
	public List<EventListEntity> getEventDetailsbetweenrange(LocalDate startdate,LocalDate enddate){
	    Date startDate = java.sql.Date.valueOf(startdate); // Convert LocalDate to java.util.Date
        Date endDate = java.sql.Date.valueOf(enddate);
		return eventListRepository.findByEventTimeBetween(startDate,endDate);
	}
	public List<EventManagerEntity> geteventManagerdetails(){
		return eventRepository.findAll();
	}
	
	public List<EventListEntity> getEventDetailsByNameOrVenue(String Name,String venue){
		return eventListRepository.findByEventNameContainingIgnoreCaseOrVenueContainingIgnoreCase(Name,venue);
	}
	
	public List<EventListEntity> getByEventCategeory(String eventCategory){
		return eventListRepository.findByeventCategory(eventCategory);
	}

	public String eventManagerLogin(String username,String password){
		Optional<EventManagerEntity> nameList = eventRepository.findByUsernameAndPassword(username,password);
	  if(nameList.isPresent()) {
		  //return "Happy to login";
		  EventManagerEntity eventEntity = nameList.get();
		  LoginRecordEntity loginEntity = new LoginRecordEntity();
		  loginEntity.setEventManagerId(eventEntity.getEventManagerId());
		  loginRepository.save(loginEntity);
		  return "The user has successfully logged in";
		  
	  }else {
		  return "please enter the correct username and password";
	  }
	}
}
