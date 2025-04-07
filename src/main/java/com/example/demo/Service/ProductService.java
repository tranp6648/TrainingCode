package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Generic.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService extends BaseService<Product, Integer> {

    Page<Product> findByCategoryPaginate(int categoryId, Pageable pageable);

    void addByCategory(int categoryId, Product product);

    Product updateProductCategory(int productId, int updateCategoryId);

    Page<Product> findAllPaginate(Pageable pageable);
}
