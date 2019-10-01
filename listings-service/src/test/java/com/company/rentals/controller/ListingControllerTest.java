package com.company.rentals.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class ListingControllerTest {

    private static final String BASE_URL = "/listings/";

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenListingIdWhenGetRequestThenReturngVacationRentalListing() throws Exception {
        URL url = ClassLoader.getSystemClassLoader().getResource("test-data.txt");
        String content = Files.readString(Paths.get(url.toURI()));

        URI createdUri = postContent(content);
        String id = createdUri.getPath().substring(BASE_URL.length());

        mockMvc.perform(get(createdUri).accept(MediaType.APPLICATION_JSON))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.listing.id")
                       .value(id))
               .andExpect(jsonPath("$.listing.contact.phone")
                       .value("15126841100"))
               .andExpect(jsonPath("$.listing.contact.formattedPhone")
                       .value("+1 512-684-1100"))
               .andExpect(jsonPath("$.listing.address.address")
                       .value("1011 W 5th St"))
               .andExpect(jsonPath("$.listing.address.postalCode")
                       .value("1011"))
               .andExpect(jsonPath("$.listing.address.countryCode")
                       .value("US"))
               .andExpect(jsonPath("$.listing.address.city")
                       .value("Austin"))
               .andExpect(jsonPath("$.listing.address.state")
                       .value("TX"))
               .andExpect(jsonPath("$.listing.address.country")
                       .value("United States"))
               .andExpect(jsonPath("$.listing.location.lat")
                       .value("40.4255485534668"))
               .andExpect(jsonPath("$.listing.location.lng")
                       .value("-3.7075681686401367"))
               .andReturn();
    }

    @Test
    public void givenListingFileWhenPostThenRequestIsOk() throws Exception {
        URL url = ClassLoader.getSystemClassLoader().getResource("test-data.txt");
        String content = Files.readString(Paths.get(url.toURI()));

        mockMvc.perform(post(BASE_URL).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    private URI postContent(String content) {
        final String baseUrl = "http://localhost:" + randomServerPort + BASE_URL;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(content, headers);

        return restTemplate.postForLocation(baseUrl, request);
    }
}