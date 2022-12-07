package com.msa.productmicroservice.controller;

import com.msa.productmicroservice.dto.CategoryDto;
import com.msa.productmicroservice.dto.ProductDto;
import com.msa.productmicroservice.dto.ResponseComDto;
import com.msa.productmicroservice.service.CategoryService;
import com.msa.productmicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<ResponseComDto> searchProduct(@RequestParam Long productId){
        ProductDto.show show = productService.searchProduct(productId);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("조회 완료")
                        .resultObj(show)
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<ResponseComDto> searchProductForOrder(ProductDto.searchProduct searchProduct){
        List<ProductDto.show> shows = productService.searchProductsForOrder(searchProduct.getProductId());
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("조회 완료")
                        .resultObj(shows)
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/category/products")
    public ResponseEntity<ResponseComDto> searchProducts(CategoryDto.searchProduct searchProduct, Pageable pageable){
        Page<ProductDto.show> shows = productService.searchProductByCategory(searchProduct, pageable);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("조회 완료")
                        .resultObj(shows)
                        .build(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseComDto> createCategory(@RequestBody ProductDto.create create){
        ProductDto.show show = productService.createProduct(create);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("생성 완료")
                        .resultObj(show)
                        .build(), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseComDto> createCategory(@PathVariable("id") Long productId){
        ProductDto.show show = productService.removeProduct(productId);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("삭제 완료")
                        .resultObj(show)
                        .build(), HttpStatus.OK);
    }
}
