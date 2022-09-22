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

import static com.example.jpa_assignment.entities.Measurement.*;


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




        Ingredient ingredient1 = ingredientRepo.save(new Ingredient("Egg"));
        Ingredient ingredient2 = ingredientRepo.save(new Ingredient("Milk"));
        Ingredient ingredient3 = ingredientRepo.save(new Ingredient("Cheese"));
        Ingredient ingredient4 = ingredientRepo.save(new Ingredient("Chicken"));
        Ingredient ingredient5 = ingredientRepo.save(new Ingredient("flour"));
        Ingredient ingredient6 = ingredientRepo.save(new Ingredient("cheddar cheese"));
        Ingredient ingredient7 = ingredientRepo.save(new Ingredient("pasta"));
        Ingredient ingredient8 = ingredientRepo.save(new Ingredient("onion"));
        Ingredient ingredient9 = ingredientRepo.save(new Ingredient("Butter"));
        Ingredient ingredient10 = ingredientRepo.save(new Ingredient("Mustard"));

        RecipeInstruction instruction1 = recipeInstructionRepo.save(new RecipeInstruction("cake instruction"));
        RecipeInstruction instruction2 = recipeInstructionRepo.save(new RecipeInstruction("Chicken grill instruction"));
        RecipeInstruction instruction3 = recipeInstructionRepo.save(new RecipeInstruction("Waffle instruction"));
        RecipeInstruction instruction4 = recipeInstructionRepo.save(new RecipeInstruction("Pasta instruction"));
        RecipeInstruction instruction5 = recipeInstructionRepo.save(new RecipeInstruction("Pancake instruction"));


        Recipe recipe1 = recipeRepo.save(new Recipe("Vanilla cake",instruction1));
        Recipe recipe2 = recipeRepo.save(new Recipe("Grill chicken",instruction2));
        Recipe recipe3 = recipeRepo.save(new Recipe("Waffle",instruction3));
        Recipe recipe4 = recipeRepo.save(new Recipe("Alfredo pasta",instruction4));
        Recipe recipe5 = recipeRepo.save(new Recipe("Mini Pancake",instruction5));

        RecipeCategory recipeCategory1 = new RecipeCategory("SeaFood");
        RecipeCategory recipeCategory2 = new RecipeCategory("Breakfast");
        RecipeCategory recipeCategory3 = new RecipeCategory("Vegetarian");
        RecipeCategory recipeCategory4 = new RecipeCategory("Appetisers");
        RecipeCategory recipeCategory5 = new RecipeCategory("Grill food");
        RecipeCategory recipeCategory6 = new RecipeCategory("Lunch");
        RecipeCategory recipeCategory7 = new RecipeCategory("Holiday");
        RecipeCategory recipeCategory8 = new RecipeCategory("Desserts");

       RecipeIngredient recipeIngredient1 = recipeIngredientRepo.save(new RecipeIngredient(ingredient5,200, TBSP));

        recipe1.addRecipeIngredient(recipeIngredient1);

        recipe1.addRecipeCategory(recipeCategory8);
        recipe2.addRecipeCategory(recipeCategory7);
        recipe2.addRecipeCategory(recipeCategory5);
        recipe2.addRecipeCategory(recipeCategory6);
        recipe5.addRecipeCategory(recipeCategory2);
        recipe5.addRecipeCategory(recipeCategory8);


//------------Ingredient Repository
        Optional<Ingredient> foundName = ingredientRepo.findIngredientByIngredientName("pasta");
        entityManager.flush();
        foundName.ifPresent(System.out::println);

        List<Ingredient> foundPartOfName = ingredientRepo.findIngredientByPartOfIngredientName("cheese");
        foundPartOfName.forEach(System.out::println);

//------------Recipe Repository
        List<Recipe> foundRecipeName = recipeRepo.findRecipesByPartOfRecipeName("ill");
        foundRecipeName.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> foundRecipeByIngredientName = recipeRepo.findRecipesByRecipeIngredientsIngredientIngredientNameIgnoreCase("flour");
        foundRecipeByIngredientName.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> foundRecipes = recipeRepo.findAllByCategoriesCategoryIgnoreCase("Desserts");
        foundRecipes.forEach(recipe -> System.out.println(recipe.getRecipeName()));

        List<Recipe> recipesFound = recipeRepo.findAllDistinctByCategoriesCategoryInIgnoreCase(Arrays.asList("Desserts", "Lunch", "Breakfast"));
        recipesFound.forEach(recipe -> System.out.println(recipe.getRecipeName()));









    }
}
