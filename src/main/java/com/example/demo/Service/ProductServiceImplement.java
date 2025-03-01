package com.example.demo.Service;

import com.example.demo.DTO.ProductByCategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean add(ProductDTO productDTO) {
        try {
            Product product = modelMapper.map(productDTO, Product.class);
            Category category = categoryRepository.findById(productDTO.getCategory_id()).orElse(null);
            product.setCategory(category);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Product update(int id, ProductDTO productDTO) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                modelMapper.map(productDTO, product);
                Category category = categoryRepository.findById(productDTO.getCategory_id()).orElse(null);
                product.setCategory(category);
                productRepository.save(product);
                return product;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<Product> findByCategoryPaginate(int categoryId, Pageable pageable) {
        try {
            return productRepository.findByCategoryIdPaginate(categoryId, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addByCategory(int categoryId, ProductByCategoryDTO productByCategoryDTO) {
        try {
            Product product = modelMapper.map(productByCategoryDTO, Product.class);
            Category category = categoryRepository.findById(categoryId).orElse(null);
            product.setCategory(category);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product updateProductCategory(int productId, int categoryId, int updateCategoryId) {
        try {
            Product product=productRepository.findByCategoryProduct(categoryId,productId);
            if(product!=null){
                Category category=categoryRepository.findById(updateCategoryId).orElse(null);
                product.setCategory(category);
                productRepository.save(product);
            }
            return product;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Page<Product> findAllPaginate(Pageable pageable) {
        try {
            return productRepository.findAllPaginate(pageable);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
