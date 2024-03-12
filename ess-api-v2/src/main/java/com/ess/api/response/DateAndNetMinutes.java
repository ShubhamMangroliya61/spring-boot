package com.ess.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class DateAndNetMinutes {
    private LocalDate date;
    private LocalTime netHours;

    @Override
    public String toString() {
        return "DateAndNetMinutes{" +
                "date=" + date +
                ", netHours=" + netHours +
                '}';
    }
}
