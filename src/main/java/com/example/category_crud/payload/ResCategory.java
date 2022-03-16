package com.example.category_crud.payload;

import com.example.category_crud.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCategory {
    public ResCategory(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentId = category.getParentCategory()!=null?category.getParentCategory().getId():null;
//        this.parentId= category.getParentCategory().getId();
    }


    private UUID id;
    private String name;
    private UUID parentId;
    private List<ResCategory> childCategories;
}
