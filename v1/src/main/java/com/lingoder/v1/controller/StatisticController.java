package com.lingoder.v1.controller;

import com.lingoder.v1.dto.StatisticResponseDto;
import com.lingoder.v1.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService service;

    public StatisticController(StatisticService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StatisticResponseDto> getStatistics() {
        return ResponseEntity.ok(service.getUserStatistics());
    }
}
