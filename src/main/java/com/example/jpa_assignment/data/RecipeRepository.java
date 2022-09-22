package com.example.jpa_assignment.data;

import com.example.jpa_assignment.entities.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

//---Search for recipes where recipe name contains specified String.
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.recipeName)  LIKE LOWER(CONCAT('%', :name , '%'))")
    List<Recipe> findRecipesByPartOfRecipeName(@Param("name") String recipeName);


//---Search for all recipes that contains a specified ingredient name.
    List<Recipe> findRecipesByRecipeIngredientsIngredientIngredientNameIgnoreCase(String ingredientName);


//---Search for all recipes that belong to a specific recipe category.e.g. Chicken, Vegan, Celebration, Weekend etc.
    List<Recipe> findAllByCategoriesCategoryIgnoreCase(String category);



//--Search for all recipes that match one or more categories. e.g. {”Spicy”,”Mexican”,”Weekend”}
    List<Recipe> findAllDistinctByCategoriesCategoryInIgnoreCase(Collection<String> category);





}
