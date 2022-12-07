package com.msa.productmicroservice.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

public class CategoryDto {
    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class create{
        private String name;
        private Integer depth;
        private Boolean isUse;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class show{
        private String name;
        private Integer depth;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class searchProduct{
        private Long categoryId;
        private Integer depth;
    }
}
