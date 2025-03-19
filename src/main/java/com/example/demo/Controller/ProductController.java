package com.example.demo.Controller;

import com.example.demo.DTO.Response.ApiResponse;
import com.example.demo.DTO.Response.ResponseUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllPaginate(@RequestParam int page, @RequestParam int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            ApiResponse apiResponse = ResponseUtil.SuccessData("List Data Paginate", productService.findAllPaginate(pageable));
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Fail Data");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        try {
            ApiResponse apiResponse = ResponseUtil.SuccessData("List Product", productService.getAll());
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse apiResponse = ResponseUtil.Error("Fail Data");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PatchMapping("{productId}/category/{categoryId}")
    public ResponseEntity<Object> getProductCategory(@PathVariable int productId, @PathVariable int categoryId, @RequestBody int category_id) {
        Product product = productService.updateProductCategory(productId, categoryId, category_id);
        if (product != null) {
            ApiResponse apiResponse = ResponseUtil.SuccessData("Product Category Updated", product);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Product Category Updated Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Product productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("Validation errors: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessages.append(String.format("Field : %s. ", error.getDefaultMessage()))
            );
            ApiResponse apiResponse = ResponseUtil.Error(errorMessages.toString());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        Product product = productService.update(id, productDTO);
        if (product != null) {
            ApiResponse apiResponse = ResponseUtil.SuccessData("Update Product Success", product);
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Update Product Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        if (productService.delete(id)) {
            ApiResponse apiResponse = ResponseUtil.SuccessNotData("Delete Product Success");
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse apiResponse = ResponseUtil.Error("Delete Product Failed");
            return ResponseEntity.badRequest().body(apiResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody Product productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder("Validation errors: ");
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessages.append(String.format("Field : %s. ", error.getDefaultMessage()))
            );
            ApiResponse apiResponse = ResponseUtil.Error(errorMessages.toString());
            return ResponseEntity.badRequest().body(apiResponse);
        }

        ApiResponse apiResponse = ResponseUtil.SuccessNotData("Add Product Success");
        return ResponseEntity.ok(apiResponse);

    }
}
