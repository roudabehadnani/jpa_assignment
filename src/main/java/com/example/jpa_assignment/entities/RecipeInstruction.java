package com.example.jpa_assignment.entities;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class RecipeInstruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeInstructionId;
    @Column(nullable = false, length = 1500)
    private String instructions;

    public RecipeInstruction() {
    }

    public RecipeInstruction(String instructions) {
        this.instructions = instructions;
    }

    public int getRecipeInstructionId() {
        return recipeInstructionId;
    }

    public void setRecipeInstructionId(int recipeInstructionId) {
        this.recipeInstructionId = recipeInstructionId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeInstruction that = (RecipeInstruction) o;
        return recipeInstructionId == that.recipeInstructionId && Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeInstructionId, instructions);
    }

    @Override
    public String toString() {
        return "RecipeInstruction{" +
                "recipeInstructionId=" + recipeInstructionId +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
