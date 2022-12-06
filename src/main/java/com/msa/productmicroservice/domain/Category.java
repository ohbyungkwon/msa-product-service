package com.msa.productmicroservice.domain;


import com.msa.productmicroservice.dto.CategoryDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Category {

    @Id
    @GeneratedValue
    private Long categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer depth;

    @Column(nullable = false)
    private Boolean isUse;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static Category create(CategoryDto.create create){
        return Category.builder()
                .isUse(create.getIsUse())
                .name(create.getName())
                .depth(create.getDepth())
                .build();
    }

    public CategoryDto.show convertShowDto(){
        return CategoryDto.show.builder()
                .name(this.name)
                .depth(this.depth)
                .build();
    }
}
