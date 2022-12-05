package com.msa.productmicroservice.repository;

import com.msa.productmicroservice.domain.Product;
import com.msa.productmicroservice.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}