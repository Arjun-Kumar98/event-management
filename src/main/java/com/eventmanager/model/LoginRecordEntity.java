package com.eventmanager.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name="Login")
public class LoginRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Integer loginId;

    @ManyToOne
    @JoinColumn(name = "eventmanager_id", nullable = true) 
    private EventManagerEntity eventManagerId;

    @ManyToOne
    @JoinColumn(name = "audience_id", nullable = true) 
    private AudienceEntity audienceId;
    

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

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
    
}
