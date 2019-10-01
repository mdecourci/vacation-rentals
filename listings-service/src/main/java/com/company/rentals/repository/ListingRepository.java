package com.company.rentals.repository;

import com.company.rentals.entity.Listing;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ListingRepository {
    
    private ConcurrentHashMap<UUID, Listing> dataStore;

    public ListingRepository() {
        this.dataStore = new ConcurrentHashMap<>();
    }

    public Optional<Listing> findById(UUID uuid) {
        Optional.ofNullable(dataStore.get(uuid));
        return Optional.ofNullable(dataStore.get(uuid));
    }
    
    public Listing save(Listing listing) {

        Listing newListing = listing.toBuilder()
                .id(UUID.randomUUID())
                .contact(listing.getContact())
                .address(listing.getAddress())
                .location(listing.getLocation()).build();

        dataStore.putIfAbsent(newListing.getId(), newListing);

        return newListing;
    }
}
