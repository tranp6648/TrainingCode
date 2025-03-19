package com.example.demo.Service;
import com.example.demo.Entity.Product;
import com.example.demo.Generic.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService extends BaseService<Product,Integer> {

    public Page<Product> findByCategoryPaginate(int categoryId, Pageable pageable);
    public boolean addByCategory(int categoryId, Product product);
    public Product updateProductCategory(int productId,int categoryId,int updateCategoryId);
    public Page<Product>findAllPaginate(Pageable pageable);
}
