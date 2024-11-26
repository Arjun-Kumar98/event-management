package com.eventmanager.service;

import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventListRepository eventListRepository;

    @Mock
    private LoginRepository loginRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveEventManagerDetails() {
    
        EventManagerEntity eventManager = new EventManagerEntity();
        eventManager.setUsername("manager1");
        eventManager.setPassword("password");

        when(eventRepository.save(any(EventManagerEntity.class))).thenReturn(eventManager);

       
        EventManagerEntity savedManager = eventService.saveEventManagerDetails(eventManager);

   
        assertNotNull(savedManager);
        assertEquals("manager1", savedManager.getUsername());
        verify(eventRepository, times(1)).save(eventManager);
    }

    @Test
    void testCreateEvent() {
        
        EventListEntity eventList = new EventListEntity();
        eventList.setEventManagerId(1);
        eventList.setEventName("Music Concert");

        EventManagerEntity manager = new EventManagerEntity();
        when(eventRepository.findById(1)).thenReturn(Optional.of(manager));
        when(loginRepository.findByEventManagerIdEventManagerId(1)).thenReturn(Optional.of(new LoginRecordEntity()));

        
        String response = eventService.createEvent(eventList);

        assertEquals("The event has been created successfully", response);
        verify(eventListRepository, times(1)).save(eventList);
    }


    @Test
    void testDeleteEventDetails() {

        when(eventRepository.existsById(1)).thenReturn(true);


        eventService.deleteEventDetails(1);


        verify(eventListRepository, times(1)).deleteById(1);
    }

 
}
