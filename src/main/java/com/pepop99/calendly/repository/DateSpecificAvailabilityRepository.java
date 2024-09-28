package com.pepop99.calendly.repository;

import com.pepop99.calendly.model.DateSpecificAvailability;
import com.pepop99.calendly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DateSpecificAvailabilityRepository extends JpaRepository<DateSpecificAvailability, Long> {

    Optional<DateSpecificAvailability> findByUserAndDate(User user, LocalDate date);
}
