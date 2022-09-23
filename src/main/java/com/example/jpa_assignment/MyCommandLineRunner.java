package com.example.jpa_assignment;

import com.example.jpa_assignment.data.IngredientRepository;
import com.example.jpa_assignment.data.RecipeIngredientRepository;
import com.example.jpa_assignment.data.RecipeInstructionRepository;
import com.example.jpa_assignment.data.RecipeRepository;
import com.example.jpa_assignment.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Transactional
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final IngredientRepository ingredientRepo;
    private final RecipeRepository recipeRepo;
    private final RecipeIngredientRepository recipeIngredientRepo;
    private final RecipeInstructionRepository recipeInstructionRepo;
    private final EntityManager entityManager;


    @Autowired
    public MyCommandLineRunner(IngredientRepository ingredientRepo, RecipeRepository recipeRepo, RecipeIngredientRepository recipeIngredientRepo, RecipeInstructionRepository recipeInstructionRepo, EntityManager entityManager) {
        this.ingredientRepo = ingredientRepo;
        this.recipeRepo = recipeRepo;
        this.recipeIngredientRepo = recipeIngredientRepo;
        this.recipeInstructionRepo = recipeInstructionRepo;
        this.entityManager = entityManager;
    }


    @Override
    public void run(String... args) throws Exception {

        Ingredient salt = ingredientRepo.save(new Ingredient("Salt"));

        RecipeInstruction instructionFish = recipeInstructionRepo.save(new RecipeInstruction("instruction for grill fish"));

        Recipe salmon = recipeRepo.save(new Recipe("Grill Salmon",instructionFish));

        RecipeCategory seaFood = new RecipeCategory("Sea Food");

        salmon.addRecipeCategory(seaFood);


//------------Ingredient Repository
        Optional<Ingredient> foundIngredientName = ingredientRepo.findIngredientByIngredientName("Pasta");
        entityManager.flush();
        foundIngredientName.ifPresent(System.out::println);

        List<Ingredient> foundPartOfName = ingredientRepo.findIngredientByPartOfIngredientName("cheese");
        foundPartOfName.forEach(System.out::println);

//------------Recipe Repository
        List<Recipe> foundRecipeName = recipeRepo.findRecipesByPartOfRecipeName("ill");
        foundRecipeName.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> foundRecipeByIngredientName = recipeRepo.findRecipesByRecipeIngredientsIngredientIngredientNameIgnoreCase("flour");
        foundRecipeByIngredientName.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> foundRecipesByCategory = recipeRepo.findAllByCategoriesCategoryIgnoreCase("Desserts");
        foundRecipesByCategory.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> recipesFound = recipeRepo.findAllDistinctByCategoriesCategoryInIgnoreCase(Arrays.asList("Desserts", "Lunch"));
        recipesFound.forEach(recipe -> System.out.println(recipe.getRecipeName()));


    }
}
