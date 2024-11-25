package com.eventmanager.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import com.eventmanager.model.*;


@Entity
@Table(name = "event_managing")
public class EventListEntity {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_category")
    @JsonProperty("eventCategory")
    private String eventCategory;

    @Column(name = "event_time")
    @JsonProperty("eventTime")
    private Timestamp eventTime;

    @Column(name = "event_name")
    @JsonProperty("eventName")
    private String eventName;
    
    @Column(name = "venue")
    @JsonProperty("venue")
    private String venue;

    @Column(name = "total_tickets")
    @JsonProperty("totalTickets")
    private Integer totalTickets;

    @ManyToOne
    @JoinColumn(name = "eventmanager_id", nullable = false) 
    private EventManagerEntity eventManagerId;

    public Integer getEventManagerId() {
        return eventManagerId != null ? eventManagerId.getEventManagerId() : null;
    }

    public void setEventManagerId(Integer eventManagerId) {
        if (eventManagerId != null) {
            EventManagerEntity eventManager = new EventManagerEntity();
            eventManager.setEventManagerId(eventManagerId);
            this.eventManagerId = eventManager;
        } else {
            this.eventManagerId = null;
        }
    }

    // Getters and Setters
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Integer totalTickets) {
        this.totalTickets = totalTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

  

}


    

