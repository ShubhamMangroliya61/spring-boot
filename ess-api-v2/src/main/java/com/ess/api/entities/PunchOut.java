package com.ess.api.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "punchout")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PunchOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "punchout_id")
    private long id;

    @Column(name = "punchout_date")
    private LocalDate date;

    @Column(name = "punchout_time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public PunchOut(Employee employee){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.date = LocalDate.now();
        this.time = LocalTime.parse(LocalTime.now().format(formatter));
        this.employee = employee;
    }

    public PunchOut(LocalDate date, LocalTime time, Employee employee) {
        this.date = date;
        this.time = time;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PunchIn [id=" + id + ", date=" + date + ", time=" + time + ", employee=" + employee + "]";
    }
}
