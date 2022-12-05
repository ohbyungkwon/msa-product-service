package com.msa.productmicroservice.domain;

import com.msa.productmicroservice.dto.ProductDto;
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
public class Product {

    @Id
    private Long productId;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Category category;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static Product create(ProductDto.create create, Category category){
        return Product.builder()
                .price(create.getPrice())
                .name(create.getName())
                .category(category)
                .build();
    }

    public ProductDto.show convertShowDto(){
        return ProductDto.show.builder()
                .price(this.price)
                .name(this.name)
                .build();
    }
}
