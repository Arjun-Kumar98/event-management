package com.eventmanager.model;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "event_audience_mapping")
public class EventMappingEntity {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "event_map_id")
	    private Integer eventMapId;

	    // Many-to-one relationship with EventEntity
	    @ManyToOne
	    @NotNull(message ="Event Id cannot be null")
	    @JoinColumn(name = "event_id", nullable = false)
	    private EventListEntity eventId;

	    // Many-to-one relationship with AudienceEntity
	    @ManyToOne
	    @NotNull(message ="Audience Id  cannot be null")
	    @JoinColumn(name = "audience_id", nullable = false)
	    private AudienceEntity audienceId;
        
	    @NotNull(message ="Total tickets cannot be null")
	    @Min(value = 1, message = "Total tickets must be at least 1")
	    @Column(name = "tickets_purchased")
	    @JsonProperty("ticketsPurchased")
	    private Integer ticketsPurchased;
	    
	    
	    public Integer getAudienceId() {
	        return audienceId != null ? audienceId.getAudienceId() : null;
	    }
	
	    public void setAudienceId(Integer audienceId) {
	        if (audienceId != null) {
	            AudienceEntity audience = new AudienceEntity();
	            audience.setAudienceId(audienceId);
	            this.audienceId = audience;
	        } else {
	            this.audienceId = null;
	        }
	    }
	    public Integer getEventId() {
	        return eventId != null ? eventId.getEventId() : null;
	    }

	    public void setEventId(Integer eventId) {
	        if (eventId != null) {
	            EventListEntity event = new EventListEntity();
	            event.setEventId(eventId);
	            this.eventId = event;
	        } else {
	            this.eventId = null;
	        }
	    }
	    
	    public Integer getTicketsPurchased() {
	    	return ticketsPurchased;
	    }
	    
	    public void setTicketsPurchased(Integer ticketsPurchased) {
	    	this.ticketsPurchased = ticketsPurchased;
	    }
	    
	    

}