package com.example.task_management.dto;

import org.springframework.data.domain.Sort;

import java.util.List;

public class SimplePage<T> {
    public final List<T> data;
    public final Long totalElements;
    public final Sort.Direction direction;
    public final String orderBy;

    public final Integer pageNumber;

    public final Integer pageSize;

    public SimplePage(List<T> data, Long totalElements, Sort.Direction direction, String orderBy, Integer pageNumber, Integer pageSize) {
        this.data = data;
        this.totalElements = totalElements;
        this.direction = direction;
        this.orderBy = orderBy;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
