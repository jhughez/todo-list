package com.sample.controller;

import com.sample.TestHelper;
import com.sample.UnitTests;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sample.model.Server;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ServerControllerTests extends UnitTests {

    final String SERVERS_URL = "/servers";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllServers() throws Exception {
        mockMvc.perform(get(SERVERS_URL)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value(23))
                .andExpect(jsonPath("$[0].name").value("Server D"));


    }

    @Test
    public void testGetServer() throws Exception {
        mockMvc.perform(get(SERVERS_URL + "/23")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value("23"))
                .andExpect(jsonPath("$.name").value("Server D"));
    }

    @Test
    public void testSaveOrUpdateServer() throws Exception {
        int serverID = 24;
        String serverName = "Controller Save or update";
        String serverDescription = "Update server";
        Server newServer = new Server(serverID, serverName, serverDescription);
        mockMvc.perform(post(SERVERS_URL + "/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestHelper.asJsonString(newServer)))
                .andExpect(status().isOk());
    }

    @Test
    public void testNewServer() throws Exception {
        int serverID = 99;
        String serverName = "New Server";
        String serverDescription = "this is a new server";
        Server newServer = new Server(serverID, serverName, serverDescription);
        mockMvc.perform(post(SERVERS_URL + "/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestHelper.asJsonString(newServer)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteServer() throws Exception {
        mockMvc.perform(delete(SERVERS_URL + "/26")).andExpect(status().isOk());
    }

    @Test
    public void getServerCount() throws Exception {
        String expectedServerCount = "4";
        mockMvc.perform(get(SERVERS_URL + "/count")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedServerCount));
    }
}
