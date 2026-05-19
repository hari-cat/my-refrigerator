package org.example.myrefrigerator.refrigerator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorRegisterRequest;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorResponse;
import org.example.myrefrigerator.refrigerator.service.RefrigeratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping
    public ResponseEntity<String> saveRefrigerator(@Valid @RequestBody RefrigeratorRegisterRequest request) {
        refrigeratorService.saveRefrigerator(request);

        return ResponseEntity.ok("나만의 냉장고가 저장되었습니다.");
    }

    // @TODO ownerId 가져오는 방식을 인증객체에서 가져오는 방식으로 수정이 필요
    @GetMapping("/{id}")
    public ResponseEntity<RefrigeratorResponse> getRefrigerator(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(refrigeratorService.getRefrigerator(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRefrigerator(@Valid @PathVariable Long id) {
        refrigeratorService.deleteRefrigerator(id);
        return ResponseEntity.ok("나만의 냉장고가 삭제되었습니다.");
    }
}
