package com.eventmanager.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="audience_details")
public class AudienceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="audience_id")
	private Integer audienceId;

	@Column(name="name", nullable=false)
	@JsonProperty("name")
	private String name;


	@Column(name="username",nullable=false)
	@JsonProperty("username")
	private String username;

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
}