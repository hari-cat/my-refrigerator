package org.example.myrefrigerator.RefrigeratorProduct.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.myrefrigerator.RefrigeratorProduct.RefrigeratorProductErrorCode;
import org.example.myrefrigerator.RefrigeratorProduct.dto.*;
import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.RefrigeratorProduct.repository.RefrigeratorProductRepository;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.global.exception.BusinessException;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.repository.ProductRepository;
import org.example.myrefrigerator.refrigerator.RefrigeratorErrorCode;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.example.myrefrigerator.refrigerator.repository.RefrigeratorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefrigeratorProductService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefrigeratorProductRepository refrigeratorProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createRefrigeratorProduct(Long userId, RefrigeratorProductCreateRequest request) {
        Refrigerator refrigerator = getRefrigerator(userId);

        RefrigeratorProduct refrigeratorProduct =
                refrigeratorProductRepository
                        .findByRefrigeratorIdAndProductIdAndExpiredAt(
                                refrigerator.getId(),
                                request.productId(),
                                request.expiredAt()
                        )
                        .orElse(null);

        if (refrigeratorProduct != null) {
            refrigeratorProduct.addQuantity(request.quantity());
            return;
        }

        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new BusinessException(RefrigeratorProductErrorCode.REFRIGERATOR_PRODUCT_IS_NOT_EXIST));

        RefrigeratorProduct newRefrigeratorProduct = RefrigeratorProduct.create(refrigerator, product, request);

        refrigeratorProductRepository.save(newRefrigeratorProduct);
    }

    public Page<RefrigeratorProductResponse> getRefrigeratorProducts(Long userId, RefrigeratorProductSearchCondition condition, Pageable pageable) {
        Refrigerator refrigerator = getRefrigerator(userId);

        log.info("refrigerator={}", refrigerator);

        Page<RefrigeratorProduct> result = refrigeratorProductRepository.search(refrigerator.getId(), condition, pageable);

        log.info("result={}", result.getContent());

        return result.map(RefrigeratorProductResponse::from);

    }

    @Transactional
    public void updateQuantity(Long userId, Long productId, UpdateQuantityRequest request) {

        Refrigerator refrigerator = getRefrigerator(userId);

        RefrigeratorProduct result = refrigeratorProductRepository.findRefrigeratorProductByIdAndRefrigeratorAndStatus(productId, refrigerator, Status.ACTIVE).orElseThrow(() -> new BusinessException(RefrigeratorProductErrorCode.REFRIGERATOR_PRODUCT_IS_NOT_EXIST));

        result.addQuantity(request.quantity());
    }

    @Transactional
    public void deleteRefrigeratorProduct(Long userId, Long productId) {
        Refrigerator refrigerator = getRefrigerator(userId);

        RefrigeratorProduct result = refrigeratorProductRepository.findRefrigeratorProductByIdAndRefrigeratorAndStatus(productId, refrigerator, Status.ACTIVE).orElseThrow(() -> new BusinessException(RefrigeratorProductErrorCode.REFRIGERATOR_PRODUCT_IS_NOT_EXIST));

        result.addQuantity(0);
        result.delete();
    }

    public StaticsRefrigeratorResponse retrieveRefrigeratorStatistics(Long userId){
        Refrigerator refrigerator = getRefrigerator(userId);

        long expiredProductCount = refrigeratorProductRepository.countExpiredProducts(refrigerator.getId());

        return StaticsRefrigeratorResponse.from(10, 1, expiredProductCount);
    }

    public Refrigerator getRefrigerator(Long userId) {
        return refrigeratorRepository
                .findActiveRefrigerator(userId)
                .orElseThrow(() -> new BusinessException(RefrigeratorErrorCode.REFRIGERATOR_NOT_FOUND));
    }
}
