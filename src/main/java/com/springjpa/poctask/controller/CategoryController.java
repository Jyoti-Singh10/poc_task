package com.springjpa.poctask.controller;

import com.springjpa.poctask.dto.CategoryCreateRequest;
import com.springjpa.poctask.entities.Category;
import com.springjpa.poctask.service.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable int id){
        Category categoryById = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryById);
    }

    @PostMapping()
    @Transactional
    @Validated
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryCreateRequest categoryCreateRequest){
        Category category = categoryService.addCategory(categoryCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCategoryById(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted the category for given id : "+id);
    }

    @PutMapping("/{id}")
    @Transactional
    @Validated
    public ResponseEntity<Category> updateCategoryById(@PathVariable int id, @RequestBody @Valid Category category){
        Category category1 = categoryService.updateCategoryById(id, category);
        return ResponseEntity.status(HttpStatus.OK).body(category1);
    }

    @GetMapping("/user/{id}")
    public List<Category> getCategoryByUserId(@PathVariable int id){
        return categoryService.getCategoryByUserId(id);
    }
}
