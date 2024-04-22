package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.CategoryCreateRequest;
import com.springjpa.poctask.entities.Category;
import com.springjpa.poctask.entities.Users;
import com.springjpa.poctask.repository.CategoryRepository;
import com.springjpa.poctask.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Id not found."));
    }

    @Override
    @Transactional
    public Category addCategory(CategoryCreateRequest category) {
        Category newCategory = new Category();
        newCategory.setTitle(category.getTitle());
        Optional<Users> byId = userRepository.findById(category.getUserId());
        byId.ifPresent(newCategory::setCreatedBy);
        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Category updateCategoryById(int id, Category category) {
        if(category.getId()!=id) throw new RuntimeException("Id not matched.");
        return categoryRepository.findById(id)
                .map(existingCategoryId-> categoryRepository.save(category))
                .orElseThrow(()->new RuntimeException("Category Id not found."));
    }

    @Override
    public List<Category> getCategoryByUserId(int id) {
        return categoryRepository.findAllByCreatedById(id);
    }
}
