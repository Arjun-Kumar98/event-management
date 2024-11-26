package com.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.eventmanager.model.*;

public interface EventRepository extends JpaRepository<EventManagerEntity,Integer> {
	//List<EventManagerEntity> findByUsernameAndPassword(String username,String password);
	
	Optional<EventManagerEntity> findByUsername(String username);
	

}
