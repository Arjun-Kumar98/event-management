package com.eventmanager.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.eventmanager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
public interface AudienceRepository extends JpaRepository<AudienceEntity,Integer> {
	Optional<AudienceEntity> findByUsernameAndPassword(String userName,String password);
}
