package org.example.myrefrigerator.refrigerator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.myrefrigerator.auth.oauth.CustomOAuth2User;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorRegisterRequest;
import org.example.myrefrigerator.refrigerator.dto.RefrigeratorResponse;
import org.example.myrefrigerator.refrigerator.service.RefrigeratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/refrigerator")
@RequiredArgsConstructor
public class RefrigeratorController {
    private final RefrigeratorService refrigeratorService;

    @PostMapping
    public ResponseEntity<String> saveRefrigerator(@AuthenticationPrincipal CustomOAuth2User principal, @Valid @RequestBody RefrigeratorRegisterRequest request) {
        refrigeratorService.saveRefrigerator(principal.getId(), request);

        return ResponseEntity.ok("나만의 냉장고가 저장되었습니다.");
    }

    // @TODO ownerId 가져오는 방식을 인증객체에서 가져오는 방식으로 수정이 필요
    @GetMapping()
    public ResponseEntity<RefrigeratorResponse> getRefrigerator(@AuthenticationPrincipal CustomOAuth2User principal) {
        return ResponseEntity.ok(refrigeratorService.getRefrigerator(principal.getId()));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteRefrigerator(@AuthenticationPrincipal CustomOAuth2User principal) {
        refrigeratorService.deleteRefrigerator(principal.getId());
        return ResponseEntity.ok("나만의 냉장고가 삭제되었습니다.");
    }
}
