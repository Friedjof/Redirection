package com.example.redirect.redirect;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class RedirectController {
    private final RedirectService redirectService;
    private final ConnectionService connectionService;

    @Autowired
    public RedirectController(RedirectService redirectService, ConnectionService connectionService) {
        this.redirectService = redirectService;
        this.connectionService = connectionService;
    }

    @GetMapping
    public List<Redirect> getRedirects() {
        return this.redirectService.getRedirects();
    }

    @RequestMapping(value = "/s/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public RedirectView getRedirect(@PathVariable String id, HttpServletRequest request) {
        Optional<Redirect> redirect = this.redirectService.getRedirect(id);
        if (redirect.isPresent()) {
            this.connectionService.saveConnection(
                    new Connection(
                            request.getRemoteAddr(),
                            request.getHeader("User-Agent"),
                            redirect.get()
                    )
            );
            return redirect.get().asRedirectView();
        } else {
            throw new IllegalStateException("Redirect not found");
        }
    }

    @GetMapping(path = "/add/{url}/{id}")
    public String addRedirect(@PathVariable String url, @PathVariable String id, HttpServletRequest request) {
        // Hänge ein "http://" vorne an
        url = url.matches("^(https?://).*") ? url : "https://" + url;

        // Check if the URL is valid (Pattern: *.XXX.XX)
        if (!url.matches("^(https?://)?(www\\.)?([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]+$")) {
            throw new IllegalStateException("Invalid URL");
        }

        try {
            this.redirectService.addRedirect(new Redirect(url, id));
        } catch (IllegalStateException e) {
            throw new IllegalStateException("ID already taken");
        }

        // Separate the domain + port from the rest of the URL
        String[] urlParts = request.getRequestURL().toString().split("/add");

        return urlParts[0] + "/s/" + id;
    }
}
