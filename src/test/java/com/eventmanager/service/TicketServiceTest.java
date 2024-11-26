package com.eventmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class TicketServiceTest {
	
	@Mock
	private LoginRepository loginRepository;
	
	@Mock
	private TicketRepository ticketRepository;
	
	@Mock
	private EventListRepository eventListRepository;
	
	@InjectMocks
	private TicketService ticketService;
	

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	 @Test
	    void testBookTickets() {
	        EventMappingEntity booking = new EventMappingEntity();
	        booking.setAudienceId(1);
	        booking.setEventId(100);
	        booking.setTicketsPurchased(2);

	        LoginRecordEntity loginRecord = new LoginRecordEntity();
	        EventListEntity event = new EventListEntity();
	        event.setEventId(100);
	        event.setTotalTickets(10);

	        when(loginRepository.findByAudienceIdAudienceId(1)).thenReturn(Optional.of(loginRecord));
	        when(eventListRepository.findById(100)).thenReturn(Optional.of(event));
	        when(ticketRepository.save(booking)).thenReturn(booking);

	        String result = ticketService.bookTickets(booking);

	        assertEquals("The tickets have been booked successfully", result);
	        assertEquals(8, event.getTotalTickets());
	        verify(eventListRepository, times(1)).findById(100);
	        verify(ticketRepository, times(1)).save(booking);
	    }
	 
	    @Test
	    void testCancelTickets() {
	        EventMappingEntity ticket = new EventMappingEntity();
	        ticket.setEventId(100);
	        ticket.setAudienceId(1);
	        ticket.setTicketsPurchased(2);

	        EventListEntity event = new EventListEntity();
	        event.setEventId(100);
	        event.setTotalTickets(10);

	        when(ticketRepository.findByEventIdEventIdAndAudienceIdAudienceId(100, 1))
	                .thenReturn(Optional.of(ticket));
	        when(eventListRepository.findById(100)).thenReturn(Optional.of(event));

	        String result = ticketService.cancelTickets(100, 1);

	        assertEquals("Your tickets have been cancelled", result);
	        assertEquals(12, event.getTotalTickets());
	        verify(ticketRepository, times(1)).delete(ticket);
	    }

	    @Test
	    void testEventList() {
	        EventMappingEntity ticket1 = new EventMappingEntity();
	        ticket1.setAudienceId(1);
	        EventMappingEntity ticket2 = new EventMappingEntity();
	        ticket2.setAudienceId(1);

	        when(ticketRepository.findByAudienceIdAudienceId(1)).thenReturn(List.of(ticket1, ticket2));

	        List<EventMappingEntity> result = ticketService.eventlist(1);

	        assertEquals(2, result.size());
	        verify(ticketRepository, times(1)).findByAudienceIdAudienceId(1);
	    }

}
