package com.ess.api.repositories;

import com.ess.api.entities.Employee;
import com.ess.api.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    public List<Leave> findByEmployee(Employee employee);
}
