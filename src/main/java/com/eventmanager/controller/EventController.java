package com.eventmanager.controller;

import com.eventmanager.service.EventService;
import com.eventmanager.model.*;
import com.eventmanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;

@RestController
@RequestMapping("/events")

public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired
    private EventService eventService;
	
	@Autowired
	private EventRepository eventRepository;
	
	  @PostMapping("/manager")
	    public ResponseEntity<EventManagerEntity> saveEventManagerDetails(@RequestBody EventManagerEntity eventManager) {
	        return ResponseEntity.ok(eventService.saveEventManagerDetails(eventManager));
	    }
	  
	  @PostMapping("/save")
	  public ResponseEntity<EventListEntity> saveEventDetails(@RequestBody EventListEntity eventListEntity){
		   logger.info("Attempting to {}", eventListEntity);

		    // Save the EventListEntity and return the response
		    return ResponseEntity.ok(eventService.saveEventDetails(eventListEntity));
	  }
	    
	    
	    @GetMapping("/nameList")
	    public ResponseEntity<String> eventManagerLogin(@RequestParam String username, @RequestParam String password) {
	 	   logger.info("Attempting to log in with username: {} and password:{}", username,password);
	        return ResponseEntity.ok(eventService.eventManagerLogin(username, password));
	    }
	    
	    @PutMapping("/updateEventDetails")
	    public ResponseEntity<EventListEntity> updateEventDetails(@RequestBody EventListEntity eventList) {
	        try {
	            return ResponseEntity.ok(eventService.updateEventDetails(eventList));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(null); 
	        }
	    }
	        @GetMapping("/viewEventList")
	        public ResponseEntity<List<EventListEntity>> viewEventDetails() {
	            return ResponseEntity.ok(eventService.viewEventDetails());
	        }
               
	        
	        
	        @DeleteMapping("/deleteEvent/{id}")
	        public ResponseEntity<String> deleteEventDetails(@PathVariable("id") Integer eventId) {
        	try {
	            eventService.deleteEventDetails(eventId);
          return ResponseEntity.ok("Event deleted successfully");
       }catch(RuntimeException e) {
        	return ResponseEntity.status(404).body("Event is not present");
	        }
	        }
	  
	        @GetMapping("/range")
	        public ResponseEntity<List<EventListEntity>> getEventDetailsBetweenRange(
	                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate) {
	            return ResponseEntity.ok(eventService.getEventDetailsbetweenrange(startDate, endDate));
	        }

	        @GetMapping("/search")
	        public ResponseEntity<List<EventListEntity>> getEventDetailsByNameOrVenue(
	                @RequestParam(required = false) String name, @RequestParam(required = false) String venue) {
	            return ResponseEntity.ok(eventService.getEventDetailsByNameOrVenue(name, venue));
	        }

	        @GetMapping("/category")
	        public ResponseEntity<List<EventListEntity>> getByEventCategory(@RequestParam String eventCategory) {
	            return ResponseEntity.ok(eventService.getByEventCategeory(eventCategory));
	        }
	      
	        @GetMapping("/viewmanagers")
	        public ResponseEntity<List<EventManagerEntity>> getEventManagerList(){
	        	return ResponseEntity.ok(eventService.geteventManagerdetails());
	        }
	        @GetMapping("/login")
	        public ResponseEntity<String> getNameList(@RequestParam String username, @RequestParam String password){
	        	return ResponseEntity.ok(eventService.eventManagerLogin(username,password));
	        }
	      
}
