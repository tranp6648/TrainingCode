package com.example.demo.Service.implement;
import com.example.demo.Entity.Category;
import com.example.demo.Exception.CRUDException;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean create(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        } catch (Exception e) {
          throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public List<Category> getAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category getById(Integer id) {
        try {
            return categoryRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category update(Integer integer, Category category) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(integer);
            if (optionalCategory.isPresent()) {
                Category categorySave = optionalCategory.get();
                categorySave.setName(category.getName());
                categorySave.setDescription(category.getDescription());
                categoryRepository.save(categorySave);
                return categorySave;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CRUDException(e.getMessage());
        }
    }
}
