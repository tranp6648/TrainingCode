package com.example.demo.Repository;

import com.example.demo.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    Page<Product> findByCategoryIdPaginate(@Param("categoryId") int categoryId, Pageable pageable);

    @Query("SELECT p from  Product  p")
    Page<Product> findAllPaginate(Pageable pageable);

    @Query("SELECT p from Product p where p.category.id = :categoryId and p.id = :productId")
    Product findByCategoryProduct(@Param("categoryId") int categoryId, @Param("productId") int productId);
}
