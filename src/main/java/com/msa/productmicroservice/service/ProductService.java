package com.msa.productmicroservice.service;

import com.msa.productmicroservice.domain.Category;
import com.msa.productmicroservice.domain.Product;
import com.msa.productmicroservice.dto.CategoryDto;
import com.msa.productmicroservice.dto.ProductDto;
import com.msa.productmicroservice.exception.BadReqException;
import com.msa.productmicroservice.repository.CategoryRepository;
import com.msa.productmicroservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public ProductDto.show searchProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadReqException("잘못된 요청입니다."));
        return product.convertShowDto();
    }

    public Page<ProductDto.show> searchProducts(CategoryDto.searchProduct searchProduct){
        return productRepository.findProductByCategoryIdAndDepth(searchProduct.getCategoryId(),
                searchProduct.getDepth(), searchProduct.getPageable());
    }

    @Transactional
    public ProductDto.show createProduct(ProductDto.create create){
        Category category = categoryRepository.findById(create.getCategoryId())
                .orElseThrow(() -> new BadReqException("잘못된 요청입니다."));
        Product product = Product.create(create, category);
        return productRepository.save(product).convertShowDto();
    }

    @Transactional
    public ProductDto.show removeProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BadReqException("잘못된 요청입니다."));
        productRepository.delete(product);
        return product.convertShowDto();
    }
}
