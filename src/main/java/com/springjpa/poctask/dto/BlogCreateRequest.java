package com.springjpa.poctask.dto;

import com.springjpa.poctask.entities.Category;
import com.springjpa.poctask.entities.Users;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCreateRequest {

    @NotEmpty
    private String title;
    private Category category;
    private Users createdBy;
    private String content;
    private BlogStatus status;
}
