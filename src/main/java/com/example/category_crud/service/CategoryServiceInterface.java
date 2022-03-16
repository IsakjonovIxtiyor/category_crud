package com.example.category_crud.service;

import com.example.category_crud.payload.ApiResponse;
import com.example.category_crud.payload.ReqCategory;
import com.example.category_crud.payload.ResCategory;
import com.example.category_crud.payload.ResPageable;

import java.util.List;
import java.util.UUID;

public interface CategoryServiceInterface {
    public ResPageable getAllCategory(int page, int size);
    public List<ResCategory> getAllParentCategory();
    public ResCategory getOne(UUID id);
    public ApiResponse editAndCreateCategory(ReqCategory reqCategory);
    public ApiResponse deleteCategory(UUID id);
}
