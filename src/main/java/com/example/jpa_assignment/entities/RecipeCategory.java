package com.example.jpa_assignment.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeCategoryId;
    @Column(length = 255, nullable = false)
    private String category;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recipe> recipes;

    public RecipeCategory() {
    }

    public RecipeCategory(int recipeCategoryId, String category, Set<Recipe> recipes) {
        this.recipeCategoryId = recipeCategoryId;
        this.category = category;
        this.recipes = recipes;
    }

    public RecipeCategory(String category) {
        this.category = category;
        setRecipes(new HashSet<>());
    }

    public int getRecipeCategoryId() {
        return recipeCategoryId;
    }

    public void setRecipeCategoryId(int recipeCategoryId) {
        this.recipeCategoryId = recipeCategoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory that = (RecipeCategory) o;
        return recipeCategoryId == that.recipeCategoryId && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeCategoryId, category);
    }

    @Override
    public String toString() {
        return "RecipeCategory{" +
                "recipeCategoryId=" + recipeCategoryId +
                ", category='" + category + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
