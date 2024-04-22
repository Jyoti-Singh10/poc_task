package com.springjpa.poctask.controller;

import com.springjpa.poctask.dto.BlogCreateRequest;
import com.springjpa.poctask.dto.BlogResponseWithoutContent;
import com.springjpa.poctask.entities.Blog;
import com.springjpa.poctask.service.BlogService;
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
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping()
    public List<BlogResponseWithoutContent> getAllBlogs(){
        return blogService.getAllBlogsWithoutContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id){
        Blog blogById = blogService.getBlogById(id);
        return ResponseEntity.status(HttpStatus.OK).body(blogById);
    }

    @PostMapping()
    @Validated
    @Transactional
    public ResponseEntity<Blog> addBlog(@RequestBody @Valid BlogCreateRequest blogCreateRequest){
        Blog blog = blogService.addBlog(blogCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlogById(@PathVariable int id){
        blogService.deleteBlogById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted the given blog.");
    }

    @PutMapping("/{id}")
    @Validated
    @Transactional
    public ResponseEntity<Blog> updateBlogById(@PathVariable int id, @RequestBody @Valid Blog blog){
        Blog blogById = blogService.updateBlogById(id, blog);
        return ResponseEntity.status(HttpStatus.OK).body(blogById);
    }

    @GetMapping("/category/{id}")
    public List<BlogResponseWithoutContent> getAllBlogsByCategoryId(@PathVariable int id){
        return blogService.getAllBlogsByCategoryId(id);
    }
    @GetMapping("/user/{id}")
    public List<BlogResponseWithoutContent> getAllBlogsByUserId(@PathVariable int id){
        return blogService.getAllBlogsByUserId(id);
    }
}
