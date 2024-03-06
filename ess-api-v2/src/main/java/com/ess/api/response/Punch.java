package com.ess.api.response;

import com.ess.api.entities.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Punch {
    private long id;
    private LocalDate date;
    private LocalTime time;
    private Employee employee;
    private boolean isPunchIn;
    private boolean isPunchOut;
}
