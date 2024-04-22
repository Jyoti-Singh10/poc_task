package com.springjpa.poctask.service;

import com.springjpa.poctask.dto.BlogCreateRequest;
import com.springjpa.poctask.dto.BlogResponseWithoutContent;
import com.springjpa.poctask.entities.Blog;
import com.springjpa.poctask.entities.Category;
import com.springjpa.poctask.entities.Users;
import com.springjpa.poctask.repository.BlogRepository;
import com.springjpa.poctask.repository.CategoryRepository;
import com.springjpa.poctask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<BlogResponseWithoutContent> getAllBlogsWithoutContent() {
        return blogRepository.findAll().stream().map(blog->BlogResponseWithoutContent.mapFrom(blog)).toList();
    }

    @Override
    public Blog getBlogById(int id) {
        return blogRepository.findById(id).orElseThrow(()->new RuntimeException("Blog Id not found."));
    }

    @Override
    public Blog addBlog(BlogCreateRequest blogCreateRequest) {
        Blog blog = new Blog();
        blog.setTitle(blogCreateRequest.getTitle());
        Optional<Category> byCategoryId = categoryRepository.findById(blogCreateRequest.getCategoryId());
        byCategoryId.ifPresent(blog::setCategory);
        Optional<Users> byUserId = userRepository.findById(blogCreateRequest.getCreatedBy());
        byUserId.ifPresent(blog::setCreatedBy);
        blog.setContent(blogCreateRequest.getContent());
        blog.setStatus(blogCreateRequest.getStatus());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlogById(int id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog updateBlogById(int blogId, Blog blog) {
        if(blogId!= blog.getId()) throw new RuntimeException("Id not matched.");
        return blogRepository.findById(blogId)
                .map(existingBlog->blogRepository.save(blog))
                .orElseThrow(()->new RuntimeException("Id not found."));
    }

    @Override
    public List<BlogResponseWithoutContent> getAllBlogsByCategoryId(int categoryId) {
        return blogRepository.findAllByCategoryId(categoryId).stream().map(BlogResponseWithoutContent::mapFrom).toList();
    }

    @Override
    public List<BlogResponseWithoutContent> getAllBlogsByUserId(int userId) {
        return blogRepository.findAllByCreatedById(userId).stream().map(BlogResponseWithoutContent::mapFrom).toList();
    }
}
