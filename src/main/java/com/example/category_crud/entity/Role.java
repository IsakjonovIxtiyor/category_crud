package com.example.category_crud.entity;

import com.example.category_crud.entity.enums.RoleEnum;
import com.example.category_crud.entity.template.IntegerTemplate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends IntegerTemplate {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
