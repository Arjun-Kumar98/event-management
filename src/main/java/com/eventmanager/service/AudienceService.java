package com.eventmanager.service;
import org.springframework.beans.factory.annotation.Autowired;
 // For dependency injection
import org.springframework.stereotype.Service; 
import com.eventmanager.model.*;
import java.util.Optional;
import com.eventmanager.repository.*;

@Service
public class AudienceService {

@Autowired
private AudienceRepository audienceRepository;

@Autowired
private LoginRepository loginRepository;

@Autowired
private PasswordService passwordService;

@Autowired
private EventRepository eventRepository;

public AudienceEntity save(AudienceEntity audienceEntity) {
	String hashedPassword = passwordService.hashPassword(audienceEntity.getPassword());
	audienceEntity.setPassword(hashedPassword);
	return audienceRepository.save(audienceEntity);
}
public String audienceLogin(String userName,String password) {
	Optional<AudienceEntity> optaudience = audienceRepository.findByUsername(userName);
		if(optaudience.isPresent() && passwordService.verifyPassword(password, optaudience.get().getPassword())) {
			AudienceEntity audienceEntity = optaudience.get();
			LoginRecordEntity loginEntity = new LoginRecordEntity();
			loginEntity.setAudienceId(audienceEntity.getAudienceId());
			loginRepository.save(loginEntity);
			return "The user has successfully logged in";
		}else {
			return "incorrect username or password";
		}
		
	}

}

