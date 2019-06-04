package com.sample.services;

import com.sample.UnitTests;
import com.sample.model.Server;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@Service
public class ServerServiceTest extends UnitTests {

    @Autowired
    ServerService serverService;

    @Test
    public void testGetAllServers() {
        int serverID = 23;
        String serverName = "Server D";
        String serverDescription = "Description of Server D";
        Server createdServer = new Server(serverID, serverName, serverDescription);
        List<Server> servers = serverService.getAllServers();
        assertNotNull(servers);
        assertTrue(servers.size() > 1);
        assertTrue(servers.contains(createdServer));
    }

    @Test
    public void testGetServerById() {
        int serverID = 23;
        String serverName = "Server D";
        String serverDescription = "This is server D";
        Server server = new Server(serverID, serverName, serverDescription);
        assertNotNull(server);
        assertTrue(server.getId() == serverID);
        assertTrue(server.getName().equals(serverName));
    }

    @Test
    public void testSaveOrUpdate() {
        int serverID = 24;
        String serverName = "Controller Save or update";
        String serverDescription = "Server updated";
        Server newServer = new Server(serverID, serverName, serverDescription);
        serverService.saveOrUpdate(newServer);
        Server returnedServer = serverService.getServerById(serverID);
        assertNotNull(returnedServer);
        assertTrue(returnedServer.getName().equals(serverName));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testAddNewServer() {
        int serverID = 50;
        String serverName = "New Server 50";
        String serverDescription = "this is a new server";
        Server newServer = new Server(serverID, serverName, serverDescription);
        serverService.addNewServer(newServer);
        Server returnedServer = serverService.getServerById(serverID);
        assertNotNull(returnedServer);
        assertTrue(returnedServer.getId() == serverID);
        serverService.addNewServer(newServer);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testAddNewServerException() {
        int serverID = 23;
        String serverName = "New Server";
        String serverDescription = "this is a new server";
        Server newServer = new Server(serverID, serverName, serverDescription);
        serverService.addNewServer(newServer);
    }

    @Test(expected = NoSuchElementException.class)
    public void delete() {
        int serverID = 24;
        Server server = serverService.getServerById(serverID);
        assertNotNull(server);
        serverService.delete(serverID);
        serverService.getServerById(serverID);
    }

    @Test()
    public void getServerCount() {
        long expectedServerCount = 4;
        assertEquals(serverService.getServerCount(), expectedServerCount);
    }
}
