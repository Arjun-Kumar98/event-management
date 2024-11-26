package com.eventmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanager.model.*;
import java.util.*;
import javax.validation.Valid;

import javax.validation.constraints.NotBlank;


import com.eventmanager.service.TicketService;
@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    @PostMapping("/book")
    public ResponseEntity<String> bookTickets(@Valid @RequestBody EventMappingEntity eventMappingEntity) {
        String response = ticketService.bookTickets(eventMappingEntity);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/cancel/{eventId}/{audienceId}")
    public ResponseEntity<String> cancelTickets(@PathVariable Integer eventId, @PathVariable Integer audienceId) {
        String response = ticketService.cancelTickets(eventId, audienceId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/bookingList")
    public ResponseEntity<List<EventMappingEntity>> viewTickets(@RequestParam @NotBlank(message="audience Id cannot be blank") Integer audienceId){
    	List<EventMappingEntity> ticketList = ticketService.eventlist(audienceId);
    	return ResponseEntity.ok(ticketList);
    }
}
