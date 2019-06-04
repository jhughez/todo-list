package com.sample.controller;

import com.sample.model.Server;
import com.sample.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServerController {

    private static final Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    ServerService serverService;

    @GetMapping("/servers")
    private List<Server> getAllServers() {
        logger.debug("getAllServers()");
        return serverService.getAllServers();
    }

    @GetMapping("/servers/{id}")
    private Server getServer(@PathVariable("id") int id) {
        logger.debug("getServer(" + id + ")");
        return serverService.getServerById(id);
    }

    @DeleteMapping("/servers/{id}")
    private void deleteServer(@PathVariable("id") int id) {
        logger.debug("deleteServer(" + id + ")");
        serverService.delete(id);
    }

    @PostMapping("/servers/update")
    private int saveOrUpdateServer(@RequestBody Server server) {
        logger.debug("saveServer(id: " + server.getId() + ", name: " + server.getName() + ")");
        serverService.saveOrUpdate(server);
        return server.getId();
    }

    @GetMapping("/servers/count")
    private long getServerCount() {
        logger.debug("getServerCount");
        return serverService.getServerCount();
    }




    @PostMapping("/servers/new")
    private ResponseEntity newServer(@RequestBody Server server) {
        logger.debug("saveServer(id: " + server.getId() + ", name: " + server.getName() + ")");
        try {
            serverService.addNewServer(server);
        } catch (DataIntegrityViolationException dte) {
            logger.error("Problem saving server: { id: " + server.getId()
                    + ", name: " + server.getName() + "} ID already exists.", dte);
            return new ResponseEntity (HttpStatus.CONFLICT);
        }
        return new ResponseEntity (HttpStatus.OK);
    }


}
