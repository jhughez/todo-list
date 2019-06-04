package com.sample.services;

import com.sample.model.Server;
import com.sample.repositories.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerService {

    private static final Logger logger = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    ServerRepository serverRepository;

    public List<Server> getAllServers() {
        logger.debug("getAllServers()");
        List<Server> servers = new ArrayList<Server>();
        serverRepository.findAll().forEach(server -> servers.add(server));
        return servers;
    }

    public Server getServerById(int id) {
        logger.debug("getServerById(" + id + ")");
        return serverRepository.findById(id).get();
    }

    public void delete(int id) {
        logger.debug("deleteServer(" + id + ")");
        serverRepository.deleteById(id);
    }

    public void saveOrUpdate(Server server) {
        logger.debug("saveOrUpdate(id: " + server.getId() + ", name: " + server.getName() + ")");
        serverRepository.save(server);
    }

    public void addNewServer(Server server) {
        logger.debug("addNewServer");
        server.setNewServer(true);
        saveOrUpdate(server);
    }

    public long getServerCount(){
        logger.debug("getServerCount");
        return serverRepository.count();
    }
}
