package com.pepop99.calendly.dto;

import com.pepop99.calendly.model.Availability;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public class DaySpecificAvailabilityDTO {
    private String email;
    private DayOfWeek dayOfWeek;
    private Availability availability;
}
