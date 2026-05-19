package org.example.myrefrigerator.RefrigeratorProduct.service;

import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductCreateRequest;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductResponse;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductSearchCondition;
import org.example.myrefrigerator.RefrigeratorProduct.dto.UpdateQuantityRequest;
import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.RefrigeratorProduct.repository.RefrigeratorProductRepository;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.repository.ProductRepository;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.example.myrefrigerator.refrigerator.repository.RefrigeratorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefrigeratorProductService {
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefrigeratorProductRepository refrigeratorProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createRefrigeratorProduct(RefrigeratorProductCreateRequest request) {
        RefrigeratorProduct refrigeratorProduct =
                refrigeratorProductRepository
                        .findByRefrigeratorIdAndProductIdAndExpiredAt(
                                request.refrigeratorId(),
                                request.productId(),
                                request.expiredAt()
                        )
                        .orElse(null);

        if (refrigeratorProduct != null) {
            refrigeratorProduct.addQuantity(request.quantity());
            return;
        }

        Refrigerator refrigerator = refrigeratorRepository.findById(request.refrigeratorId()).orElseThrow(() -> new IllegalArgumentException("refrigerator is not exist"));
        Product product = productRepository.findById(request.productId()).orElseThrow(() ->new IllegalArgumentException("product is not exist"));

        RefrigeratorProduct newRefrigeratorProduct = RefrigeratorProduct.create(refrigerator, product, request);

        refrigeratorProductRepository.save(newRefrigeratorProduct);
    }

    public Page<RefrigeratorProductResponse> getRefrigeratorProducts(RefrigeratorProductSearchCondition condition, Pageable pageable){
        // @TODO 실제 냉장고 유저가 존재하는지 확인하는 로직 필요함.
        Page<RefrigeratorProduct> result = refrigeratorProductRepository.search(condition, pageable);

        return result.map(RefrigeratorProductResponse::from);

    }

    @Transactional
    public void updateQuantity(Long id, UpdateQuantityRequest request){
        RefrigeratorProduct result = refrigeratorProductRepository.findRefrigeratorProductByIdAndStatus(id, Status.ACTIVE).orElseThrow(() -> new IllegalArgumentException("냉장고에 해당 제품이 없습니다."));

        int quantity = result.addQuantity(request.quantity());
        if(quantity == 0) result.delete();
    }
}
