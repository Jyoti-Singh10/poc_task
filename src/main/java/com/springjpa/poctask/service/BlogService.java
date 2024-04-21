package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.BlogCreateRequest;
import com.springjpa.poctask.dto.BlogResponseWithoutContent;
import com.springjpa.poctask.entities.Blog;

import java.util.List;

public interface BlogService {

    List<BlogResponseWithoutContent> getAllBlogsWithoutContent();

    Blog getBlogById(int id);

    Blog addBlog(BlogCreateRequest blogCreateRequest);

    void deleteBlogById(int id);

    Blog updateBlogById(int blogId, Blog blog);

    List<BlogResponseWithoutContent> getAllBlogsByCategoryId(int categoryId);

    List<BlogResponseWithoutContent> getAllBlogsByUserId(int userId);
}
