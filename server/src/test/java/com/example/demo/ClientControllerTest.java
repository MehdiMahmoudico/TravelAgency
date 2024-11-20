package com.example.demo;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.ClientController;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setEmail("test@example.com");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setPhone("1234567890");
    }

    @Test
    public void testCreateClient() throws Exception {
        // Mock the client service to return the client when save is called
        when(clientService.saveClient(client)).thenReturn(client);

        // Perform the POST request to the /clients endpoint
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated()); // Expecting status 201 Created
    }
}
