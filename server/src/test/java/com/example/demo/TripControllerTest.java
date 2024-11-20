package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTrips() throws Exception {
        mockMvc.perform(get("/api/trips"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateTrip() throws Exception {
        String newTrip = "{\"destination\":\"Paris\", \"availableSeats\":20}";

        mockMvc.perform(post("/api/trips")
               .contentType(MediaType.APPLICATION_JSON)
               .content(newTrip))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.destination").value("Paris"));
    }
}

