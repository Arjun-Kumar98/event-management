package com.eventmanager.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="audience_details")
public class AudienceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="audience_id")
	private Integer audienceId;

	@NotNull(message ="Audience name cannot be null")
	@Column(name="name", nullable=false)
	@JsonProperty("name")
	private String name;

	@NotNull(message ="username cannot be null")
	@Column(name="username",nullable=false)
	@JsonProperty("username")
	private String username;

	@NotNull(message ="password cannot be null")
	@Column(name="password",nullable=false)
	@JsonProperty("password")
	private String password;

public Integer getAudienceId()
{
	return audienceId;
}
public void setAudienceId(Integer audienceId) {
	this.audienceId = audienceId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}