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
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllReservations() throws Exception {
        mockMvc.perform(get("/api/reservations"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateReservation() throws Exception {
        String newReservation = "{\"status\":\"PENDING\", \"clientId\":1, \"tripId\":1}";

        mockMvc.perform(post("/api/reservations")
               .contentType(MediaType.APPLICATION_JSON)
               .content(newReservation))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testUpdateReservationStatus() throws Exception {
        mockMvc.perform(put("/api/reservations/1/status")
               .contentType(MediaType.APPLICATION_JSON)
               .content("\"CONFIRMED\""))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }
}

