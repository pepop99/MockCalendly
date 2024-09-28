package com.pepop99.calendly.service;

import com.pepop99.calendly.dto.DateSpecificAvailabilityDTO;
import com.pepop99.calendly.dto.DaySpecificAvailabilityDTO;
import com.pepop99.calendly.model.Availability;
import com.pepop99.calendly.model.DateSpecificAvailability;
import com.pepop99.calendly.model.DaySpecificAvailability;
import com.pepop99.calendly.model.User;
import com.pepop99.calendly.repository.DateSpecificAvailabilityRepository;
import com.pepop99.calendly.repository.DaySpecificAvailabilityRepository;
import com.pepop99.calendly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    private DateSpecificAvailabilityRepository dateSpecificAvailabilityRepository;
    @Autowired
    private DaySpecificAvailabilityRepository daySpecificAvailabilityRepository;
    @Autowired
    private UserRepository userRepository;

    public DaySpecificAvailability setDaySpecificAvailability(DaySpecificAvailabilityDTO dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isPresent()) {
            Optional<DaySpecificAvailability> availability = daySpecificAvailabilityRepository.findByUserAndDayOfWeek(user.get(), dto.getDayOfWeek());
            if (availability.isPresent()) {
                DaySpecificAvailability daySpecificAvailability = availability.get();
                daySpecificAvailability.getAvailability().getTimeSlotList().clear();
                daySpecificAvailability.getAvailability().getTimeSlotList().addAll(dto.getAvailability().getTimeSlotList());
                daySpecificAvailabilityRepository.save(daySpecificAvailability);
                return daySpecificAvailability;
            }
            DaySpecificAvailability daySpecificAvailability = new DaySpecificAvailability();
            daySpecificAvailability.setUser(user.get());
            daySpecificAvailability.setDayOfWeek(dto.getDayOfWeek());
            daySpecificAvailability.setAvailability(dto.getAvailability());
            return daySpecificAvailabilityRepository.save(daySpecificAvailability);
        }
        throw new IllegalArgumentException("Email not found");
    }

    public DateSpecificAvailability setDateSpecificAvailability(DateSpecificAvailabilityDTO dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if (user.isPresent()) {
            Optional<DateSpecificAvailability> availability = dateSpecificAvailabilityRepository.findByUserAndDate(user.get(), dto.getDate());
            if (availability.isPresent()) {
                DateSpecificAvailability dateSpecificAvailability = availability.get();
                dateSpecificAvailability.getAvailability().getTimeSlotList().clear();
                dateSpecificAvailability.getAvailability().getTimeSlotList().addAll(dto.getAvailability().getTimeSlotList());
                dateSpecificAvailabilityRepository.save(dateSpecificAvailability);
                return dateSpecificAvailability;
            }
            DateSpecificAvailability dateSpecificAvailability = new DateSpecificAvailability();
            dateSpecificAvailability.setUser(user.get());
            dateSpecificAvailability.setAvailability(dto.getAvailability());
            dateSpecificAvailability.setDate(dto.getDate());
            dateSpecificAvailabilityRepository.save(dateSpecificAvailability);
            return dateSpecificAvailability;
        }
        throw new IllegalArgumentException("Email not found");
    }

    public Availability getUserAvailabilityOnDate(String email, LocalDate date) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Optional<DateSpecificAvailability> dateSpecificAvailability = dateSpecificAvailabilityRepository.findByUserAndDate(user.get(), date);
            if (dateSpecificAvailability.isPresent()) {
                return dateSpecificAvailability.get().getAvailability();
            }
            Optional<DaySpecificAvailability> daySpecificAvailability = daySpecificAvailabilityRepository.findByUserAndDayOfWeek(user.get(), date.getDayOfWeek());
            if (daySpecificAvailability.isPresent()) {
                return daySpecificAvailability.get().getAvailability();
            }
            return new Availability();
        }
        throw new IllegalArgumentException("Email not found");
    }
}
