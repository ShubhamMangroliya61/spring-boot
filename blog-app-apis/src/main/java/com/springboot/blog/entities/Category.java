package com.springboot.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.element.Name;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer categoryId;

    @Column(name = "title", unique = true, nullable = false)
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;
}
