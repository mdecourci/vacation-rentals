package com.company.rentals.controller;

import com.company.rentals.entity.Listing;
import com.company.rentals.entity.VacationRentalListing;
import com.company.rentals.repository.ListingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/listings")
@Slf4j
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    @GetMapping("{id}")
    public VacationRentalListing getVacationRentalListing(@PathVariable("id") String uuid) {
        log.info("Fetching listings for uuid=[{}]", uuid);

        Optional<Listing> listing =  listingRepository.findById(UUID.fromString(uuid));
        return VacationRentalListing.builder()
                .listing(listing.get())
                .build();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createVacationRentalListing(@RequestBody VacationRentalListing vacationRentalListing) {

        log.info("Create new listing {}", vacationRentalListing);

        Listing listing = listingRepository.save(vacationRentalListing.getListing());

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(listing.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
