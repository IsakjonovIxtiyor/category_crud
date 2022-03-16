package com.example.category_crud.repository;

import com.example.category_crud.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

//     List<Category>  findAllByParentCategoryIsNull();
     @Query(value = "select * from category where parent_category_id is null",nativeQuery = true)
    List<Category> findParents();

     List<Category> findAllByParentCategoryId(UUID parentCategory_id);
     List<Category> findAllByParentCategory(Category parentCategory);
     @Query(value = "select * from category where parent_category_id:=parentId",nativeQuery = true)
    List<Category> findChildByParentId(UUID parentId);
}
