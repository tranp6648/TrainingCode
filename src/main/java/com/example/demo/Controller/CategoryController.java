package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @PostMapping("{categoryId}/products")
    public ResponseEntity<Object> addProductByCategory(@PathVariable int categoryId, @Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("Validation errors: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessages.append(String.format("Field : %s. ", error.getDefaultMessage()))
            );
            ApiResponse apiResponse = ResponseUtil.Error(errorMessages.toString());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        if (productService.addByCategory(categoryId, product)) {
            ApiResponse apiResponse = ResponseUtil.SuccessNotData("Add product By Category success");
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.SuccessNotData("Add product By Category failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("{categoryId}/products")
    public ResponseEntity<Object> findByCategoryPaginate(@PathVariable int categoryId,
                                                         @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            ApiResponse apiResponse = ResponseUtil.SuccessData("List Product By Categories", productService.findByCategoryPaginate(categoryId, pageable));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Fail Data");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Category category, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("Validation errors: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessages.append(String.format("Field : %s. ", error.getDefaultMessage()))
            );
            ApiResponse apiResponse = ResponseUtil.Error(errorMessages.toString());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        if (categoryService.create(category)) {
            ApiResponse apiResponse = ResponseUtil.SuccessNotData("Category added successfully");
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Category Added Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }


    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("Validation errors: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessages.append(String.format("Field : %s. ", error.getDefaultMessage()))
            );
            ApiResponse apiResponse = ResponseUtil.Error(errorMessages.toString());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Category updateCategory = categoryService.update(id, category);
        if (updateCategory != null) {
            ApiResponse apiResponse = ResponseUtil.SuccessData("Category updated successfully", updateCategory);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Category Updated Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getCategoriesById(@PathVariable int id) {
        try {
            ApiResponse apiResponse = ResponseUtil.SuccessData("Category By Id", categoryService.getById(id));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Fail Data");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            ApiResponse apiResponse = ResponseUtil.SuccessData("List Categories", categoryService.getAll());
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Fail Data");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        if (categoryService.delete(id)) {
            ApiResponse apiResponse = ResponseUtil.SuccessNotData("Category deleted successfully");
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Category Deleted Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
