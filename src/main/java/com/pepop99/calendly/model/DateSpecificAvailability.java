package com.pepop99.calendly.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(
        name = "date_specific_availability",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "date"})
)
public class DateSpecificAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Embedded
    private Availability availability;
}
