package com.pepop99.calendly.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;

@Setter
@Getter
@Entity
@Table(
        name = "day_specific_availability",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "dayOfWeek"})
)
//can be cached in memory because it won't be changed super frequently
public class DaySpecificAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    private Availability availability;

}
