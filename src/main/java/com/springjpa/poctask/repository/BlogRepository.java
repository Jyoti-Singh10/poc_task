package com.springjpa.poctask.repository;

import com.springjpa.poctask.dto.BlogResponseWithoutContent;
import com.springjpa.poctask.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByCategoryId(int id);

    List<Blog> findAllByCreatedById(int id);
}
