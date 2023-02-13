package com.example.redirect.redirect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedirectRepository extends JpaRepository<Redirect, Long> {
    @Query("SELECT r FROM Redirect r WHERE r.redirectParameter = ?1")
    Optional<Redirect> getRedirect(String id);
}
