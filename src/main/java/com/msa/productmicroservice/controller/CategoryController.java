package com.msa.productmicroservice.controller;

import com.msa.productmicroservice.dto.CategoryDto;
import com.msa.productmicroservice.dto.ResponseComDto;
import com.msa.productmicroservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<ResponseComDto> searchCategory(@RequestParam boolean isUse){
        List<CategoryDto.show> shows = categoryService.searchCategoryList(isUse);
        return new ResponseEntity<ResponseComDto>(
                    ResponseComDto.builder()
                            .resultMsg("조회 완료")
                            .resultObj(shows)
                            .build(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseComDto> createCategory(@RequestBody CategoryDto.create create){
        CategoryDto.show show = categoryService.createCategory(create);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("생성 완료")
                        .resultObj(show)
                        .build(), HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ResponseComDto> createCategory(@PathVariable("id") Long categoryId){
        CategoryDto.show show = categoryService.removeCategory(categoryId);
        return new ResponseEntity<ResponseComDto>(
                ResponseComDto.builder()
                        .resultMsg("삭제 완료")
                        .resultObj(show)
                        .build(), HttpStatus.OK);
    }
}
