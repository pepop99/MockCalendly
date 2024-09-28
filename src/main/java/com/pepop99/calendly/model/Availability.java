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

    private Availability(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public Availability findOverlappingSlots(Availability other) {
        final List<TimeSlot> overlappingSlots = new ArrayList<>();
        for (TimeSlot thisSlot : this.timeSlotList) {
            for (TimeSlot otherSlot : other.getTimeSlotList()) {
                final LocalTime maxStart = thisSlot.startTime().isAfter(otherSlot.startTime())
                        ? thisSlot.startTime() : otherSlot.startTime();
                final LocalTime minEnd = thisSlot.endTime().isBefore(otherSlot.endTime())
                        ? thisSlot.endTime() : otherSlot.endTime();
                if (maxStart.isBefore(minEnd)) {
                    overlappingSlots.add(new TimeSlot(maxStart, minEnd));
                }
            }
        }
        return new Availability(overlappingSlots);
    }

    @Override
    public String toString() {
        return timeSlotList.toString();
    }
}
