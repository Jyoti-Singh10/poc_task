package com.springjpa.poctask.dto;

import com.springjpa.poctask.entities.Blog;
import com.springjpa.poctask.entities.Category;
import com.springjpa.poctask.entities.Users;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponseWithoutContent {

    private int id;
    private String title;
    private Category category;
    private Users createdBy;
    private BlogStatus status;

    public static BlogResponseWithoutContent mapFrom(Blog blog){
        return new BlogResponseWithoutContent(blog.getId(), blog.getTitle(), blog.getCategory(), blog.getCreatedBy(), blog.getStatus());
    }
}
