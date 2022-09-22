package com.example.jpa_assignment.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String recipeIngredientId;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH}
               , fetch = FetchType.EAGER)
    private Ingredient ingredient;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Measurement measurement;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(String recipeIngredientId, Ingredient ingredient, double amount, Measurement measurement, Recipe recipe) {
        this.recipeIngredientId = recipeIngredientId;
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public RecipeIngredient(Ingredient ingredient, double amount, Measurement measurement) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.measurement = measurement;
    }

    public RecipeIngredient(double amount, Measurement measurement) {
        this.amount = amount;
        this.measurement = measurement;
    }

    public String getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(String recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return recipeIngredientId == that.recipeIngredientId && Double.compare(that.amount, amount) == 0 && measurement == that.measurement;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeIngredientId, amount, measurement);
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "recipeIngredientId=" + recipeIngredientId +
                ", ingredient=" + ingredient +
                ", amount=" + amount +
                ", measurement=" + measurement +
                ", recipe=" + recipe +
                '}';
    }
}
