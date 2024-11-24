package com.eventmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.eventmanager.model.*;

public interface TicketRepository extends JpaRepository<EventMappingEntity,Integer> {
	
public List<EventMappingEntity> findByAudienceIdAudienceId(Integer audienceId);

public Optional<EventMappingEntity> findByEventIdEventIdAndAudienceIdAudienceId(Integer eventId,Integer audienceId);
}
