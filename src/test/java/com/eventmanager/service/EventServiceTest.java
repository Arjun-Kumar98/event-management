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
    private PasswordService passwordService;

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
        String hashedPassword = "$2a$10$eImiTXuWVxfM37uY4JANj.QWxrJh4i68eJbO.Zr6US6C2.TD1Z6/u";
        when(passwordService.hashPassword("password")).thenReturn(hashedPassword);
        when(eventRepository.save(any(EventManagerEntity.class))).thenReturn(eventManager);

       
        EventManagerEntity savedManager = new EventManagerEntity();
        savedManager.setUsername("manager1");
        savedManager.setPassword(hashedPassword);
        when(eventRepository.save(any(EventManagerEntity.class))).thenReturn(savedManager);

     
        EventManagerEntity result = eventService.saveEventManagerDetails(eventManager);

   
        assertNotNull(result);
        assertEquals("manager1", result.getUsername());
        assertEquals(hashedPassword,result.getPassword());
        verify(passwordService,times(1)).hashPassword("password");
        verify(eventRepository, times(1)).save(any(EventManagerEntity.class));
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
