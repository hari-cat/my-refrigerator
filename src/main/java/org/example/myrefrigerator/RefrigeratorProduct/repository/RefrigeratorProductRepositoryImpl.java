package org.example.myrefrigerator.RefrigeratorProduct.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductSearchCondition;
import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.product.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class RefrigeratorProductRepositoryImpl implements RefrigeratorProductCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<RefrigeratorProduct> search(Long refrigeratorId, RefrigeratorProductSearchCondition condition, Pageable pageable) {


        List<RefrigeratorProduct> contents = jpaQueryFactory
                .selectFrom(refrigeratorProduct)
                .join(refrigeratorProduct.product, product)
                .where(
                        refrigeratorIdEq(refrigeratorId),
                        categoryEq(condition.category()),
                        containName(condition.name()),
                        refrigeratorProduct.status.eq(Status.ACTIVE)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(refrigeratorProduct.expiredAt.asc())
                .fetch();

        Long total = jpaQueryFactory
                .select(refrigeratorProduct.count())
                .from(refrigeratorProduct)
                .join(refrigeratorProduct.product, product)
                .where(
                        refrigeratorIdEq(refrigeratorId),
                        categoryEq(condition.category()),
                        containName(condition.name()),
                        refrigeratorProduct.status.eq(Status.ACTIVE)
                )
                .fetchOne();

        return new PageImpl<>(
                contents,
                pageable,
                total == null ? 0 : total
        );

    }
    private BooleanExpression refrigeratorIdEq(Long refrigeratorId) {
        return refrigeratorId != null
                ? refrigeratorProduct.refrigerator.id.eq(refrigeratorId)
                : null;
    }
    private BooleanExpression containName(String name){
        return name != null && !name.isBlank()
                ? product.name.contains(name)
                : null;
    }
    private BooleanExpression categoryEq(ProductCategory category){
        return category != null
                ? product.category.eq(category)
                : null;
    }
}
