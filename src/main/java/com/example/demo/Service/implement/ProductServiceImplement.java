package com.example.demo.Service.implement;

import com.example.demo.Entity.Product;
import com.example.demo.Exception.CRUDException;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplement(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findByCategoryPaginate(int categoryId, Pageable pageable) {
        try {
            return productRepository.findByCategoryIdPaginate(categoryId, pageable);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public void addByCategory(int categoryId, Product product) {
        try {
            product.setCategoryId(categoryId);
            productRepository.save(product);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public Product updateProductCategory(int productId, int updateCategoryId) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {

                product.setCategoryId(updateCategoryId);
                productRepository.save(product);
            }
            return product;
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }

    }

    @Override
    public Page<Product> findAllPaginate(Pageable pageable) {
        try {
            return productRepository.findAllPaginate(pageable);
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }


    @Override
    public boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer integer) {
        try {
            productRepository.deleteById(integer);
            return true;
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public Product getById(Integer integer) {
        try {
            return productRepository.findById(integer).orElseThrow(() -> new CRUDException("Product Not Found:" + integer));
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public Product update(Integer integer, Product product) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(integer);
            if (optionalProduct.isPresent()) {
                Product productDto = optionalProduct.get();
                productDto.setName(product.getName());
                productDto.setPrice(product.getPrice());
                productDto.setCategoryId(product.getCategoryId());
                productRepository.save(productDto);
                return productDto;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }
}
