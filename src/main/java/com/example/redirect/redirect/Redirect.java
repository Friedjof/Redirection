package com.example.redirect.redirect;

import jakarta.persistence.*;
import org.springframework.web.servlet.view.RedirectView;

@Table(
        name = "Redirect",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "redirect_url_unique",
                        columnNames = {
                                "url"
                        }
                )
        }
)
@Entity(name = "Redirect")
public class Redirect {
    @Id
    @SequenceGenerator(
            name = "redirect_sequence",
            sequenceName = "redirect_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "redirect_sequence"
    )

    private Long id = 0L;
    private String url;
    private String redirectParameter;

    public Redirect(Long id, String url) {
        this.id = id;
        this.url = url;
        this.redirectParameter = generateID();
    }

    public Redirect(String url, String redirectParameter) {
        this.url = url;
        this.redirectParameter = redirectParameter;
    }

    public Redirect() {
        this.redirectParameter = generateID();
    }

    /*
     * ID Generator
     * This function will generate a random String of length 8 Characters and numbers
     */
    public String generateID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            id.append(characters.charAt(index));
        }
        return id.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRedirectParameter() {
        return redirectParameter;
    }

    public void setRedirectParameter(String redirectParameter) {
        this.redirectParameter = redirectParameter;
    }

    public RedirectView asRedirectView() {
        return new RedirectView(this.url);
    }

    @Override
    public String toString() {
        return "Redirect{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", redirectParameter='" + redirectParameter + '\'' +
                '}';
    }
}
