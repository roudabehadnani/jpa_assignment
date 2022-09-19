package com.example.jpa_assignment.data;

import com.example.jpa_assignment.entities.Ingredient;
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
class IngredientRepositoryTest {

    public List<Ingredient> ingredients(){
        return Arrays.asList(
                new Ingredient("Egg"),
                new Ingredient("Milk"),
                new Ingredient("flour"),
                new Ingredient("Mustard"),
                new Ingredient("cheddar cheese"),
                new Ingredient("mayonnaise"),
                new Ingredient("Butter"),
                new Ingredient("Cheese"),
                new Ingredient("chicken")
        );
    }

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    IngredientRepository testRepo;

    Ingredient testIngredient;

    @BeforeEach
    void setUp() {
        List<Ingredient> persistedIngredient = ingredients().stream()
                .map(entityManager:: persist)
                .collect(Collectors.toList());
        testIngredient = persistedIngredient.get(0);
    }

    @Test
    void findIngredientByPartOfIngredientName() {
        String name = "cheese";
        List<Ingredient> found = testRepo.findIngredientByPartOfIngredientName(name);
        assertEquals(2,found.size());

    }

}