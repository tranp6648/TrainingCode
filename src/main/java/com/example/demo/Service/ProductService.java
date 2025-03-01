package com.example.demo.Service;

import com.example.demo.DTO.ProductByCategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    public boolean add(ProductDTO productDTO);

    public List<Product> findAll();

    public Product update(int id, ProductDTO productDTO);

    public boolean delete(int id);

    public Page<Product> findByCategoryPaginate(int categoryId, Pageable pageable);

    public boolean addByCategory(int categoryId, ProductByCategoryDTO productByCategoryDTO);
    public Product updateProductCategory(int productId,int categoryId,int updateCategoryId);
    public Page<Product>findAllPaginate(Pageable pageable);
}
