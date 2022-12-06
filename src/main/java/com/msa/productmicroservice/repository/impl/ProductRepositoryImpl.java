package com.msa.productmicroservice.repository.impl;

import static com.msa.productmicroservice.domain.QProduct.product;
import static com.msa.productmicroservice.domain.QCategory.category;

import com.msa.productmicroservice.dto.ProductDto;
import com.msa.productmicroservice.dto.QProductDto_show;
import com.msa.productmicroservice.repository.custom.ProductRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ProductDto.show> findProductByCategoryIdAndDepth(Long categoryId,
                                                                 Integer depth, Pageable pageable){
        QueryResults<ProductDto.show> queryResults = jpaQueryFactory
                .select(new QProductDto_show(
                        product.price, product.name
                )).from(product)
                .join(product.category, category)
                .where(category.categoryId.eq(categoryId)
                        .and(category.depth.eq(depth)))
                .orderBy(product.name.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ProductDto.show> list = queryResults.getResults();
        long totalCnt = queryResults.getTotal();
        return new PageImpl<ProductDto.show>(list, pageable, totalCnt);
    }
}
