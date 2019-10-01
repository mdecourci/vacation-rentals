package com.company.rentals.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder(builderClassName = "Builder", toBuilder = true)
@Data
@AllArgsConstructor
public class VacationRentalListing {

    @JsonProperty("listing")
    private Listing listing;

    @Tolerate
    public VacationRentalListing() {
    }
}