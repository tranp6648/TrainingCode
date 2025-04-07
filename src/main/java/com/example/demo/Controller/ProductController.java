package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPaginate(@RequestParam int page, @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            ApiResponse<Page<Product>> apiResponse = ResponseUtil.SuccessData("List Data Paginate", productService.findAllPaginate(pageable));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        try {
            ApiResponse<List<Product>> apiResponse = ResponseUtil.SuccessData("List Product", productService.getAll());
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getProduct(@PathVariable int id) {
        try {
            ApiResponse<Product> apiResponse = ResponseUtil.SuccessData("List Data", productService.getById(id));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PatchMapping("{productId}/category")
    public ResponseEntity<Object> updateProductCategory(@PathVariable int productId, @RequestBody int category_id) {
        try {
            Product product = productService.updateProductCategory(productId, category_id);
            ApiResponse<Product> apiResponse = ResponseUtil.SuccessData("Product Category Updated", product);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Product productDTO) {
        try {
            Product product = productService.update(id, productDTO);
            ApiResponse<Product> apiResponse = ResponseUtil.SuccessData("Update Product Success", product);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try {
            productService.delete(id);
            ApiResponse<Void> apiResponse = ResponseUtil.SuccessNotData("Delete Product Success");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }

    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Product productDTO) {
        try {
            productService.create(productDTO);
            ApiResponse<Void> apiResponse = ResponseUtil.SuccessNotData("Add Product Success");
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            ApiResponse<Void> apiResponse = ResponseUtil.Error(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
}
