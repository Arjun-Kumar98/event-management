package com.eventmanager.model;
import javax.persistence.*;
import java.util.*;
import com.eventmanager.model.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="event_manager_details")
public class EventManagerEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="event_manager_id")
private Integer eventManagerId;

@Column(name="name", nullable=false)
@JsonProperty("name")
private String name;

@Column(name="username",nullable=false)
@JsonProperty("username")
private String username;

@Column(name="password",nullable=false)
@JsonProperty("password")
private String password;


public String getPassword() {
    return password;
}


public void setPassword(String password) {
    this.password = password;
}



public Integer getEventManagerId() {
    return eventManagerId;
}

public void setEventManagerId(Integer eventManagerId) {
    this.eventManagerId = eventManagerId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}
}




