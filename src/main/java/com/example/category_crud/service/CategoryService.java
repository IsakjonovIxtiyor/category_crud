package com.example.category_crud.service;

import com.example.category_crud.entity.Category;
import com.example.category_crud.payload.ApiResponse;
import com.example.category_crud.payload.ReqCategory;
import com.example.category_crud.payload.ResCategory;
import com.example.category_crud.payload.ResPageable;
import com.example.category_crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceInterface {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResPageable getAllCategory(int page, int size) {
        Page<Category> all = categoryRepository.findAll(PageRequest.of(page, size));
        ResPageable resPageable = new ResPageable();
        Page<ResCategory> map = all.map(ResCategory::new);
        resPageable.setData(map.getContent());
        resPageable.setTotalElements(map.getTotalElements());
        resPageable.setTotalPages(map.getTotalPages());
        return resPageable;


    }

    @Override
    public List<ResCategory> getAllParentCategory() {
        return categoryRepository.findParents().stream().map(ResCategory::new).collect(Collectors.toList());
    }

    @Override
    public ResCategory getOne(UUID id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            Category category = byId.get();
            ResCategory resCategory = new ResCategory(category);
            List<Category> childCategory = categoryRepository.findAllByParentCategory(category);
            List<ResCategory> collect = childCategory.stream().map(ResCategory::new).collect(Collectors.toList());
            resCategory.setChildCategories(collect);
            return  resCategory;

        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponse editAndCreateCategory(ReqCategory reqCategory) {
        try {
            Category category = new Category();
            if (reqCategory.getId() != null) {
                categoryRepository.findById(reqCategory.getId()).orElseThrow(() ->
                        new IllegalStateException("Region with this id not found"));
            }
            category.setName(reqCategory.getName());
            if (reqCategory.getParentId()!=null){
                Optional<Category> parent = categoryRepository.findById(reqCategory.getParentId());
                if (parent.isPresent()){
                    category.setParentCategory(parent.get());
                }
            }
            categoryRepository.save(category);
            return new ApiResponse(reqCategory.getId() != null ? "Edit" : "Save", true);
        } catch (Exception e) {
            return new ApiResponse("Id yoq", false);
        }
    }

    @Override
    public ApiResponse deleteCategory(UUID id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            categoryRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");

        }
    }
}
