package org.example.myrefrigerator.RefrigeratorProduct.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductCreateRequest;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductResponse;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductSearchCondition;
import org.example.myrefrigerator.RefrigeratorProduct.dto.UpdateQuantityRequest;
import org.example.myrefrigerator.RefrigeratorProduct.service.RefrigeratorProductService;
import org.example.myrefrigerator.auth.oauth.CustomOAuth2User;
import org.example.myrefrigerator.global.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refrigerator-product")
public class RefrigeratorProductController {
    private final RefrigeratorProductService refrigeratorProductService;

    @PostMapping
    public ResponseEntity<String> createRefrigeratorProduct(@AuthenticationPrincipal CustomOAuth2User user, @Valid @RequestBody RefrigeratorProductCreateRequest request) {
        refrigeratorProductService.createRefrigeratorProduct(user.getId(), request);
        return ResponseEntity.ok("해당 상품을 냉장고에 " + request.quantity() + "개 넣었습니다.");
    }

    @GetMapping
    public ResponseEntity<PageResponse<RefrigeratorProductResponse>> getRefrigeratorProducts(@AuthenticationPrincipal CustomOAuth2User user, @Valid @ModelAttribute RefrigeratorProductSearchCondition request, @RequestParam int size, @RequestParam(defaultValue = "1") int page) {
        int zeroBasedPage = Math.max(page - 1, 0); // 0들어오면 -1 되는 문제 방지
        Pageable adjustedPageable =
                PageRequest.of(zeroBasedPage, size);
        Page<RefrigeratorProductResponse> result = refrigeratorProductService.getRefrigeratorProducts(user.getId(), request, adjustedPageable);
        return ResponseEntity.ok(PageResponse.from(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateQuantity(@AuthenticationPrincipal CustomOAuth2User user, @PathVariable Long id, @Valid @RequestBody UpdateQuantityRequest request) {
        refrigeratorProductService.updateQuantity(user.getId(), id, request);
        return ResponseEntity.ok("물품수량이 변경되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRefrigeratorProduct(@AuthenticationPrincipal CustomOAuth2User user, @PathVariable Long id){
        refrigeratorProductService.deleteRefrigeratorProduct(user.getId(), id);
        return ResponseEntity.ok("해당 상품이 냉장고에서 제거되었습니다.");
    }
}
