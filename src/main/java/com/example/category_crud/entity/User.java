package com.example.category_crud.entity;

import com.example.category_crud.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true,exclude = {"followers","follows"})
@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbsEntity {

    private String name;
    private String surname;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> followers;
    @ManyToMany(mappedBy = "followers",fetch = FetchType.LAZY)
    private Set<User> follows;

}
