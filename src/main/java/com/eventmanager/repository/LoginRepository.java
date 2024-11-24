package com.eventmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.eventmanager.model.*;
import java.util.*;
public interface LoginRepository extends JpaRepository<LoginRecordEntity,Integer> {
	
  
	
	boolean existsByaudienceId(Integer audienceId);
	
	boolean existsByEventManagerId(Integer eventManagerId);
	
	Optional<EventManagerEntity> findByeventManagerId(Integer id);
	
	Optional<LoginRecordEntity> findByAudienceIdAudienceId(Integer audienceId);
	
	Optional<LoginRecordEntity> findByEventManagerIdEventManagerId(Integer eventManagerId);
}
