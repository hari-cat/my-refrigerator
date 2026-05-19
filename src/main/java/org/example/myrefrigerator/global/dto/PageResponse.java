package org.example.myrefrigerator.global.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(

        List<T> content,
        long totalElements,
        boolean hasNext,
        int page,
        int size,
        int totalPages

) {

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getTotalElements(),
                page.hasNext(),
                page.getNumber(),
                page.getTotalPages(),
                page.getSize()
        );
    }
}