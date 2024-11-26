package com.eventmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventmanager.model.AudienceEntity;
import com.eventmanager.service.AudienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;



@RestController
@RequestMapping("/audience")

public class AudienceController {
	private static final Logger logger = LoggerFactory.getLogger(AudienceController.class);

	

    @Autowired
    private AudienceService audienceService;


    @PostMapping("/save")
    public ResponseEntity<AudienceEntity> saveAudience(@RequestBody AudienceEntity audienceEntity) {
        AudienceEntity savedAudience = audienceService.save(audienceEntity);
        return ResponseEntity.ok(savedAudience);
    }
    
    @Validated
    @GetMapping("/login")
    public ResponseEntity<String> audienceLogin(@RequestParam("username") @NotBlank(message="username cannot be blank") String username, 
                                                @RequestParam("password") @NotBlank(message="password cannot be blank") String password) {
        String response = audienceService.audienceLogin(username, password);
        return ResponseEntity.ok(response);
    }
}

