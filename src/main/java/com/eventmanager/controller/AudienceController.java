package com.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanager.model.AudienceEntity;
import com.eventmanager.service.AudienceService;
@RestController
@RequestMapping("/audience")

public class AudienceController {

	

    @Autowired
    private AudienceService audienceService;


    @PostMapping("/save")
    public ResponseEntity<AudienceEntity> saveAudience(@RequestBody AudienceEntity audienceEntity) {
        AudienceEntity savedAudience = audienceService.save(audienceEntity);
        return ResponseEntity.ok(savedAudience);
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> audienceLogin(@RequestParam("username") String username, 
                                                @RequestParam("password") String password) {
        String response = audienceService.audienceLogin(username, password);
        return ResponseEntity.ok(response);
    }
}

