package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Trip;
import com.example.demo.repository.TripRepository;
import com.example.demo.repository.TripsPaginaRepository;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripsPaginaRepository tripsPaginaRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }
    public Page<Trip> searchTripsByDestination(String destination, Pageable pageable) {
        return tripRepository.findByDestinationContainingIgnoreCase(destination, pageable);
    }

    public List<Trip> getTripsByDestination(String destination) {
        return tripRepository.findByDestination(destination);
    }

    public List<Trip> getTripsWithAvailableSeats() {
        return tripRepository.findByAvailableSeatsGreaterThan(0);
    }

    public Trip saveTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
    private static final int PAGE_SIZE = 5;
    public Page<Trip> tripsPerPage(int pageNumber) {
        // get all the Books page and sort them in ascending order the last name property
        PageRequest pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.Direction.ASC, "id");
        Page<Trip> ninjas = tripsPaginaRepository.findAll(pageRequest);
        return tripsPaginaRepository.findAll(pageRequest);
    }
    
}
