package com.pepop99.calendly.model;

import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public record TimeSlot(LocalTime startTime, LocalTime endTime) {
}
