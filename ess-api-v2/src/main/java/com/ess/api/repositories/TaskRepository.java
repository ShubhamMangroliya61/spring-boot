package com.ess.api.repositories;

import com.ess.api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findTaskByName(String name);
}
