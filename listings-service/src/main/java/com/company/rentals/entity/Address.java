package com.company.rentals.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(builderClassName = "Builder")
@Data
@AllArgsConstructor
public class Address{

	@JsonProperty("country")
	private String country;

	@JsonProperty("address")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("state")
	private String state;
}