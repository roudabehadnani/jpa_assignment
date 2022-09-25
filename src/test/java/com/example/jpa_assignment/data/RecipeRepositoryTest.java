package com.example.jpa_assignment.data;

import com.example.jpa_assignment.entities.Ingredient;
import com.example.jpa_assignment.entities.Recipe;
import com.example.jpa_assignment.entities.RecipeInstruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {

        RecipeInstruction instruction1 = new RecipeInstruction("cake instruction");
        RecipeInstruction instruction2 = new RecipeInstruction("Chicken grill instruction");
        RecipeInstruction instruction3 = new RecipeInstruction("Waffle instruction");


    public List<Recipe> recipes(){
        return Arrays.asList(
                new Recipe("Vanilla cake",instruction1),
                new Recipe("Grill chicken",instruction2),
                new Recipe("Waffle",instruction3)
        );
    }

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    RecipeRepository testRepo;

    Recipe testRecipe;

    @BeforeEach
    void setUp() {
        List<Recipe> persistedRecipe = recipes().stream()
                .map(entityManager:: persist)
                .collect(Collectors.toList());
        testRecipe = persistedRecipe.get(0);
    }

    @Test
    void findRecipesByPartOfRecipeName() {
        String name = "ke";
        List<Recipe> found = testRepo.findRecipesByPartOfRecipeName(name);
        assertEquals(2, found.size());
    }
}