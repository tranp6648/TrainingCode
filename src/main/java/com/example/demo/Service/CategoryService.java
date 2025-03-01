package com.example.demo.Service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Entity.Category;

import java.util.List;

public interface CategoryService {
    public boolean addCategory(CategoryDTO categoryDTO);

    public List<Category> getAll();

    public Category getCategoryById(int id);

    public Category update(int id, CategoryDTO categoryDTO);

    public boolean delete(int id);
}
