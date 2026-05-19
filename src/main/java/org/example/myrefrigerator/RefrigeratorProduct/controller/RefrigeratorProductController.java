package org.example.myrefrigerator.RefrigeratorProduct.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductCreateRequest;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductResponse;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductSearchCondition;
import org.example.myrefrigerator.RefrigeratorProduct.dto.UpdateQuantityRequest;
import org.example.myrefrigerator.RefrigeratorProduct.service.RefrigeratorProductService;
import org.example.myrefrigerator.global.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/refrigerator-product")
public class RefrigeratorProductController {
    private final RefrigeratorProductService refrigeratorProductService;

    @PostMapping
    public ResponseEntity<String> createRefrigeratorProduct(@Valid @RequestBody RefrigeratorProductCreateRequest request) {
        refrigeratorProductService.createRefrigeratorProduct(request);
        return ResponseEntity.ok("refrigeratorProduct create success");
    }

    @GetMapping
    public ResponseEntity<PageResponse<RefrigeratorProductResponse>> refrigeratorProducts(@Valid @ModelAttribute RefrigeratorProductSearchCondition request, @RequestParam int size, @RequestParam(defaultValue = "1") int page) {
        int zeroBasedPage = Math.max(page - 1, 0); // 0들어오면 -1 되는 문제 방지
        Pageable adjustedPageable =
                PageRequest.of(zeroBasedPage, size);
        Page<RefrigeratorProductResponse> result = refrigeratorProductService.getRefrigeratorProducts(request, adjustedPageable);
        return ResponseEntity.ok(PageResponse.from(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateQuantity(@PathVariable Long id, @Valid @RequestBody UpdateQuantityRequest request) {
        refrigeratorProductService.updateQuantity(id, request);
        return ResponseEntity.ok("물품수량이 변경되었습니다.");
    }
}
