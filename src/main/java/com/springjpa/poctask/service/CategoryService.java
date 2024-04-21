package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.CategoryCreateRequest;
import com.springjpa.poctask.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(int id);

    Category addCategory(CategoryCreateRequest category);

    void deleteCategoryById(int id);

    Category updateCategoryById(int id, Category category);

    List<Category> getCategoryByUserId(int id);
}
