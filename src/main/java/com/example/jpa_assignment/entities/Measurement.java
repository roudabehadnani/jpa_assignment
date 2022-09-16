package com.example.jpa_assignment.entities;

public enum Measurement {

    TBSP("tablespoon",1),
    TSP("teaspoon", 1),
    G("grams", 1),
    HG("hectogram",1),
    KG("kilogram", 1),
    ML("milliliter",1),
    DL("deciliter",1);

    private final String name;
    private final double weight;

    Measurement(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
