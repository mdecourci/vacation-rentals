package com.company.rentals.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(builderClassName = "Builder")
@Data
@AllArgsConstructor
public class Contact{

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("formattedPhone")
	private String formattedPhone;
}