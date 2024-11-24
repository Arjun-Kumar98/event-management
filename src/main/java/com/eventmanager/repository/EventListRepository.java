package com.eventmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.eventmanager.model.*;

public interface EventListRepository extends JpaRepository<EventListEntity,Integer> {
List<EventListEntity> findByEventTimeBetween(Date startTime,Date endTime);

List<EventListEntity> findByEventNameContainingIgnoreCaseOrVenueContainingIgnoreCase(String eventName,String venue);

List<EventListEntity> findByeventCategory(String eventCategory);
}
