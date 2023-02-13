package com.example.redirect.redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionService {
    final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Iterable<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public void saveConnection(Connection connection) {
        connectionRepository.save(connection);
    }

    public void deleteConnection(Connection connection) {
        connectionRepository.delete(connection);
    }

    public void deleteAllConnections() {
        connectionRepository.deleteAll();
    }
}
