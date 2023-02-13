package com.example.redirect.redirect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
