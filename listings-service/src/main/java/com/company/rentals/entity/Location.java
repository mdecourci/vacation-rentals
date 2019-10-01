package com.company.rentals.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(builderClassName = "Builder")
@Data
@AllArgsConstructor
public class Location{

	@JsonProperty("lng")
	private double longitude;

	@JsonProperty("lat")
	private double latitude;
}