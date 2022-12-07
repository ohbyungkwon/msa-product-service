package com.msa.productmicroservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

public class ProductDto {
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class create{
        private Integer price;
        private String name;
        private Long categoryId;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    public static class show{
        private Integer price;
        private String name;

        @QueryProjection
        public show(Integer price, String name) {
            this.price = price;
            this.name = name;
        }
    }



    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchProduct{
        private List<Long> productId;
    }
}
