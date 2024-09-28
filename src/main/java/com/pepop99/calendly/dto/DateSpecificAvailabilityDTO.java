package com.pepop99.calendly.dto;

import com.pepop99.calendly.model.Availability;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateSpecificAvailabilityDTO {
    private String email;
    private LocalDate date;
    private Availability availability;
}
