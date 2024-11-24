package com.eventmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class TicketService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired EventListRepository eventListRepository;
	
	public String bookTickets(EventMappingEntity eventMappingEntity) {

		      if(eventMappingEntity.getTicketsPurchased()<=0) {
		    	  return "Please purchase atleast 1 ticket";
		      }else {
		    	  Optional<EventListEntity> Optionalevent = eventListRepository.findById(eventMappingEntity.getEventId());
		    	  if(Optionalevent.isPresent()) {
		    		  EventListEntity event = Optionalevent.get();
		    		  if(eventMappingEntity.getTicketsPurchased()>event.getTotalTickets()) {
		    			  return "These many tickets are not available";
		    		  }else {
		    			  event.setTotalTickets(event.getTotalTickets()-eventMappingEntity.getTicketsPurchased());		
		    			  ticketRepository.save(eventMappingEntity);
		    			  return "The tickets have been booked successfully";
		    			  }
		    	  }else {
		    		  return "The event is not present";
		    	  }
		      }
		
	}
	
	public List<EventMappingEntity> eventlist(Integer audienceId){
		return ticketRepository.findByAudienceIdAudienceId(audienceId);
	}
	
	public String cancelTickets(Integer eventId,Integer audienceId) {
		Optional<EventMappingEntity> bookingEntity = ticketRepository.findByEventIdEventIdAndAudienceIdAudienceId(eventId, audienceId);
		if(bookingEntity.isPresent()) {
			EventMappingEntity cancelevent = bookingEntity.get();
			
			Optional<EventListEntity> Optionalevent = eventListRepository.findById(eventId);
		
				EventListEntity event = Optionalevent.get();
				event.setTotalTickets(event.getTotalTickets()-cancelevent.getTicketsPurchased());
				ticketRepository.delete(cancelevent);
				return "Your tickets have been cancelled";
			
		}else {
			return "You have not booked tickets for this event";
		}
		
	}
}
