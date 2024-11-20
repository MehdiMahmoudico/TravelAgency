package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    // Default constructor
    public Trip() {}

    // Parameterized constructor
    public Trip(String destination, LocalDate startDate, LocalDate endDate, double price, int availableSeats) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}