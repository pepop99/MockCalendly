package com.pepop99.calendly.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Availability {
    @ElementCollection
    private final List<TimeSlot> timeSlotList;

    public Availability() {
        timeSlotList = new ArrayList<>();
    }

    public void addTimeSlot(LocalTime startTime, LocalTime endTime) {
        TimeSlot newSlot = new TimeSlot(startTime, endTime);
        timeSlotList.add(newSlot);
    }

    @Override
    public String toString() {
        return timeSlotList.toString();
    }
}
