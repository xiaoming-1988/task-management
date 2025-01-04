package com.example.task_management.dto;

import org.springframework.data.domain.Sort;

import java.util.List;

public record SimplePage<T>(List<T> data,
                            Long totalElements,
                            Sort.Direction direction,
                            String orderBy,
                            Integer pageNumber,
                            Integer pageSize) {
}
