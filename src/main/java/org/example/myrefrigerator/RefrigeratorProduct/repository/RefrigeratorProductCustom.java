package org.example.myrefrigerator.RefrigeratorProduct.repository;

import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductSearchCondition;
import org.example.myrefrigerator.RefrigeratorProduct.entity.QRefrigeratorProduct;
import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.product.entity.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RefrigeratorProductCustom {
    Page<RefrigeratorProduct> search(
            Long refrigeratorId,
            RefrigeratorProductSearchCondition condition,
            Pageable pageable
    );

    QRefrigeratorProduct refrigeratorProduct = QRefrigeratorProduct.refrigeratorProduct;
    QProduct product = QProduct.product;
}
