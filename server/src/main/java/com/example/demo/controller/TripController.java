package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;







import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



import com.example.demo.model.Trip;
import com.example.demo.service.TripService;






@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "http://localhost:3000") 
public class TripController {

    @Autowired
    private TripService tripService;
    @CrossOrigin(origins = "http://localhost:3000") 
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Optional<Trip> trip = tripService.getTripById(id);
        return trip.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.saveTrip(trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Trip>> searchTrips(
            @RequestParam("destination") String destination,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        
        if (destination == null || destination.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Trip> trips = tripService.searchTripsByDestination(destination, pageable);
        return ResponseEntity.ok(trips);
    }
}

