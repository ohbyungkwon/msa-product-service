package com.msa.productmicroservice.repository.custom;

import com.msa.productmicroservice.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<ProductDto.show> findProductByCategoryIdAndDepth(Long categoryId,
                                                          Integer depth, Pageable pageable);
}
