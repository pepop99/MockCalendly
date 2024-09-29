package com.pepop99.calendly.dto;

import com.pepop99.calendly.model.Availability;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.DayOfWeek;

@Getter
public class DaySpecificAvailabilityDTO {
    @Schema(example = "rishabharora1780@gmail.com")
    private String email;
    @Schema(example = "MONDAY")
    private DayOfWeek dayOfWeek;
    @Schema(example = """
            {
                    "timeSlotList": [
                        {
                            "startTime": "09:00:00",
                            "endTime": "17:00:00"
                        }
                    ]
                }""")
    private Availability availability;
}
