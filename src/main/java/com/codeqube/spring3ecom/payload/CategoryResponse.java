package com.codeqube.spring3ecom.payload;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    List<CategoryDTO> content;

    public List<CategoryDTO> getContent() {
        return content;
    }

    public void setContent(List<CategoryDTO> content) {
        this.content = content;
    }
}
