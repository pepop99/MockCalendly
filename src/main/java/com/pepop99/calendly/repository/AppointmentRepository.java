package com.pepop99.calendly.repository;

import com.pepop99.calendly.model.Appointment;
import com.pepop99.calendly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser1OrUser2AndAppointmentDate(User user1, User user2, LocalDate appointmentDate);
}
