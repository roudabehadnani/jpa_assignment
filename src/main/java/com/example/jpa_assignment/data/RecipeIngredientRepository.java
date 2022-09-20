package com.example.jpa_assignment.data;

import com.example.jpa_assignment.entities.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {

}
