package com.example.demo.Service.implement;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Exception.CRUDException;
import com.example.demo.Repository.CategoryRepository;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
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
    public boolean addByCategory(int categoryId, Product product) {
        try{
            Category category = categoryRepository.findById(categoryId).orElse(null);
            product.setCategory(category);
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
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
            throw new CRUDException(e.getMessage());
        }

    }

    @Override
    public Page<Product> findAllPaginate(Pageable pageable) {
        try {
            return productRepository.findAllPaginate(pageable);
        }catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }


    @Override
    public boolean create(Product product) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        try {
            productRepository.deleteById(integer);
            return true;
        }catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            return productRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product getById(Integer integer) {
        return null;
    }

    @Override
    public Product update(Integer integer, Product product) {
        try {
            Optional<Product>optionalProduct=productRepository.findById(integer);
            if(optionalProduct.isPresent()){
                Product productdto=optionalProduct.get();
                Category category=categoryRepository.findById(product.getCategory().getId()).get();

                productdto.setName(product.getName());
                productdto.setPrice(product.getPrice());
                productdto.setCategory(category);
                productRepository.save(productdto);
                return productdto;
            }else{
                return null;
            }
        }catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }
}
