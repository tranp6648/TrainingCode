package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostMapping("{categoryId}/products")
    public ResponseEntity<Object> addProductByCategory(@PathVariable int categoryId, @RequestBody Product product) {
        try {
            productService.addByCategory(categoryId, product);
            ApiResponse<Void> apiResponse = ResponseUtil.SuccessNotData("Add product By Category success");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("{categoryId}/products")
    public ResponseEntity<Object> findByCategoryPaginate(@PathVariable int categoryId,
                                                         @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            ApiResponse<Page<Product>> apiResponse = ResponseUtil.SuccessData("List Product By Categories", productService.findByCategoryPaginate(categoryId, pageable));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Category category) {
        try {
            categoryService.create(category);
            ApiResponse<Void> apiResponse = ResponseUtil.SuccessNotData("Category added successfully");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Category category) {
        try {
            Category updateCategory = categoryService.update(id, category);
            ApiResponse<Category> apiResponse = ResponseUtil.SuccessData("Category updated successfully", updateCategory);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable int id) {
        try {
            ApiResponse<Category> apiResponse = ResponseUtil.SuccessData("Category By Id", categoryService.getById(id));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            ApiResponse<List<Category>> apiResponse = ResponseUtil.SuccessData("List Categories", categoryService.getAll());
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            categoryService.delete(id);
            ApiResponse<Void> apiResponse = ResponseUtil.SuccessNotData("Category deleted successfully");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
