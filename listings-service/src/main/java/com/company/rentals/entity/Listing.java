package com.company.rentals.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
@Data
public class Listing{

	@JsonProperty("address")
	private Address address;

	@JsonProperty("contact")
	private Contact contact;

	@JsonProperty("location")
	private Location location;

	@JsonProperty("id")
	private UUID id;
}