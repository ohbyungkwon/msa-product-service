package com.msa.productmicroservice.repository;

import com.msa.productmicroservice.domain.Category;
import com.msa.productmicroservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsUse(boolean isUse);
}