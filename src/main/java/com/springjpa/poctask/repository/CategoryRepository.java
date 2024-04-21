package com.springjpa.poctask.repository;

import com.springjpa.poctask.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

   List<Category> findAllByCreatedById(int id);
}
