package com.example.redirect.redirect;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(
        name = "Connection",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "connection_ip_redirect_id_unique",
                        columnNames = {
                                "id",
                        }
                )
        }
)
@Entity(name = "Connection")
public class Connection {
    @Id
    @SequenceGenerator(
            name = "connection_sequence",
            sequenceName = "connection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_sequence"
    )

    private Long id;
    private String ip;
    private LocalDateTime dateTime;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "redirect_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Redirect redirect;
    private String header;

    public Connection (String ip, String header, Redirect redirectId) {
        this.ip = ip;
        this.redirect = redirectId;
        this.dateTime = LocalDateTime.now();
        this.header = header;
    }

    public Connection() {

    }
    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
