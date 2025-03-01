package com.example.demo.Config;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapConfiguration {
    @Bean
    public ModelMapper modelMap() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Category.class, CategoryDTO.class).addMappings(mapping -> {
            mapping.map(category -> category.getName(), CategoryDTO::setName);
            mapping.map(category -> category.getDescription(), CategoryDTO::setDescription);
        });
        modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapping -> {
            mapping.map(product -> product.getName(), ProductDTO::setName);
            mapping.map(product -> product.getPrice(), ProductDTO::setPrice);
        });

        return modelMapper;
    }
}
