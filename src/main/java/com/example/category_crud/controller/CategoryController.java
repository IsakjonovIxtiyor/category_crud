package com.example.category_crud.controller;

import com.example.category_crud.payload.ReqCategory;
import com.example.category_crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public HttpEntity<?> getAllCategory(@RequestParam(value = "page",defaultValue = "10") int page,@RequestParam(value = "size",defaultValue = "10") int size){

        return ResponseEntity.ok().body(categoryService.getAllCategory(page,size));
    }

    @GetMapping("/parents")
    public HttpEntity<?> getAllParentCategory(){
        return ResponseEntity.ok().body(categoryService.getAllParentCategory());
    }

    @GetMapping("{id}")
    public HttpEntity<?> getOneCategory(@PathVariable  UUID id){
        return ResponseEntity.ok().body(categoryService.getOne(id));
    }
    @PostMapping
    public HttpEntity<?> editAndAddCategory(@RequestBody ReqCategory reqCategory){
        return ResponseEntity.ok().body(categoryService.editAndCreateCategory(reqCategory));
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteCategory(@PathVariable UUID id){
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

}
