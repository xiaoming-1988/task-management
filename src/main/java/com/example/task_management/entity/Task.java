package com.example.task_management.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    @SequenceGenerator(name = "task_generator", sequenceName = "task_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String type;

    private boolean done;

    private Long priority;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String description;
}
