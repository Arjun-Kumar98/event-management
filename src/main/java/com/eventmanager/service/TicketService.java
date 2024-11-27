package com.eventmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service; 
import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TicketService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired EventListRepository eventListRepository;
	
	public String bookTickets(EventMappingEntity eventMappingEntity) {
		Optional<LoginRecordEntity> audicheck = loginRepository.findByAudienceIdAudienceId(eventMappingEntity.getAudienceId()); // to check if the audience has logged im
		if (audicheck.isEmpty()) {
			 throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Audience has not logged in");
		}
		Optional<EventListEntity> Optionalevent = eventListRepository.findById(eventMappingEntity.getEventId()); // to check if the event is present
		if (Optionalevent.isEmpty()) {
			return "The event is not present";
		}
		EventListEntity event = Optionalevent.get();
		if (eventMappingEntity.getTicketsPurchased() > event.getTotalTickets()) { // to check if the tickets are available
			return "These many tickets are not available";
		}
		event.setTotalTickets(event.getTotalTickets() - eventMappingEntity.getTicketsPurchased());
		ticketRepository.save(eventMappingEntity);
		return "The tickets have been booked successfully";
	}
		      
               
	public List<EventMappingEntity> eventlist(Integer audienceId){ // to view the ticket booking history of an audience
		return ticketRepository.findByAudienceIdAudienceId(audienceId);
	}
	
	public String cancelTickets(Integer eventId, Integer audienceId) {
		Optional<EventMappingEntity> bookingEntity = ticketRepository
				.findByEventIdEventIdAndAudienceIdAudienceId(eventId, audienceId); // to check if the audience has booked tickets for the event
		
		if (bookingEntity.isPresent()) {
			EventMappingEntity cancelevent = bookingEntity.get();

			Optional<EventListEntity> Optionalevent = eventListRepository.findById(eventId);

			EventListEntity event = Optionalevent.get();
			event.setTotalTickets(event.getTotalTickets() + cancelevent.getTicketsPurchased());
			ticketRepository.delete(cancelevent);
			return "Your tickets have been cancelled";

		} else {
			return "You have not booked tickets for this event";
		}

	}
}
