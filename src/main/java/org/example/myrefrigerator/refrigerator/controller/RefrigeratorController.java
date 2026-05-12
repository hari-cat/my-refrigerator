package org.example.myrefrigerator.refrigerator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorRegisterRequest;
import org.example.myrefrigerator.refrigerator.service.RefrigeratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping
    public ResponseEntity<?> saveRefrigerator(@Valid @RequestBody RefrigeratorRegisterRequest request){
        refrigeratorService.saveRefrigerator(request);

        return ResponseEntity.ok("Register refrigerator success");
    }
}
