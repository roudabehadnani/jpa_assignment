package com.example.jpa_assignment.data;

import com.example.jpa_assignment.entities.RecipeInstruction;
import org.springframework.data.repository.CrudRepository;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, Integer> {
}
