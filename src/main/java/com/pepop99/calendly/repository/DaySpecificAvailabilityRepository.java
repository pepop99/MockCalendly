package com.pepop99.calendly.repository;

import com.pepop99.calendly.model.DaySpecificAvailability;
import com.pepop99.calendly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface DaySpecificAvailabilityRepository extends JpaRepository<DaySpecificAvailability, Long> {

    Optional<DaySpecificAvailability> findByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek);
}
