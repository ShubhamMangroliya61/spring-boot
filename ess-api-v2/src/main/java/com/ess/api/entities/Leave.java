package com.ess.api.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "leaves")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leave {

    public enum LeaveType {
        PAID, UNPAID;

    }
    public enum LeaveStatus {
        PENDING, APPROVED, REJECTED;

    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name =  "leave_type", nullable = false)
    private LeaveType type;

    @Column(name = "leave_reason")
    private String reason;

    @Column(name =  "leave_from")
    private LocalDate from;

    @Column(name = "leave_to")
    private LocalDate to;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    @Column(name = "leave_days", columnDefinition = "BIGINT DEFAULT 1")
    private long days;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_status", columnDefinition = "VARCHAR(20) DEFAULT 'PENDING'")
    private LeaveStatus status;

    @Override
    public String toString() {
        return "Leave [id=" + id + ", type=" + type + ", from=" + from + ", to=" + to + ", employee=" + employee
                + ", days=" + days + ", status=" + status + "]";
    }


    public long calculateTotalLeaveDays(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }

}
