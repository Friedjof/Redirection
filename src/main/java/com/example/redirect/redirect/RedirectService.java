package com.example.redirect.redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Component
public class RedirectService {

    private final RedirectRepository redirectRepository;

    @Autowired
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public List<Redirect> getRedirects() {
        return this.redirectRepository.findAll();
    }

    public Optional<Redirect> getRedirect(String redirectParameter) {
        return this.redirectRepository.getRedirect(redirectParameter);
    }

    public void addRedirect(Redirect redirect) throws IllegalStateException {
        // If the ID is already taken, throw an error
        if (this.redirectRepository.getRedirect(redirect.getRedirectParameter()).isPresent()) {
            throw new IllegalStateException("ID already taken");
        }
        this.redirectRepository.save(redirect);
    }
}
