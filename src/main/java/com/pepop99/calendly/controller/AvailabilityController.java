package com.pepop99.calendly.controller;

import com.pepop99.calendly.dto.DateSpecificAvailabilityDTO;
import com.pepop99.calendly.dto.DaySpecificAvailabilityDTO;
import com.pepop99.calendly.model.Availability;
import com.pepop99.calendly.model.DateSpecificAvailability;
import com.pepop99.calendly.model.DaySpecificAvailability;
import com.pepop99.calendly.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/email/{email}/date/{date}")
    public Availability getUserAvailability(@PathVariable String email,
                                            @PathVariable LocalDate date) {
        return availabilityService.getUserAvailabilityOnDate(email, date);
    }

    @GetMapping("/user1/{email1}/user2/{email2}/date/{date}")
    public Availability getUserAvailability(@PathVariable String email1, @PathVariable String email2,
                                            @PathVariable LocalDate date) {
        Availability user1Availability = availabilityService.getUserAvailabilityOnDate(email1, date);
        Availability user2Availability = availabilityService.getUserAvailabilityOnDate(email2, date);
        return user1Availability.findOverlappingSlots(user2Availability);
    }

    @PostMapping("/saveDaySpecific")
    public DaySpecificAvailability saveDaySpecificAvailability(@RequestBody DaySpecificAvailabilityDTO dto) {
        return availabilityService.setDaySpecificAvailability(dto);
    }

    @PostMapping("/saveDateSpecific")
    public DateSpecificAvailability saveDateSpecificAvailability(@RequestBody DateSpecificAvailabilityDTO dto) {
        return availabilityService.setDateSpecificAvailability(dto);
    }
}
