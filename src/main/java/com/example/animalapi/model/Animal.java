package com.example.animalapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "animals") // Adjust table name if needed
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animalId; // Unique identifier for each animal

    @Column(nullable = false)
    private String name; // Name of the animal

    @Column(nullable = true)
    private String scientificName; // Scientific name of the animal

    @Column(nullable = false)
    private String species; // Species of the animal

    @Column(nullable = false)
    private String habitat; // Habitat where the animal is found

    @Column(nullable = true)
    private String description; // Description of the animal

    // Getters and Setters
    public int getAnimalId() {
        return animalId; // Corrected getter for animalId
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId; // Setter for animalId
    }

    public String getName() {
        return name; // Getter for name
    }

    public void setName(String name) {
        this.name = name; // Setter for name
    }

    public String getScientificName() {
        return scientificName; // Getter for scientificName
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName; // Setter for scientificName
    }

    public String getSpecies() {
        return species; // Getter for species
    }

    public void setSpecies(String species) {
        this.species = species; // Setter for species
    }

    public String getHabitat() {
        return habitat; // Getter for habitat
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat; // Setter for habitat
    }

    public String getDescription() {
        return description; // Getter for description
    }

    public void setDescription(String description) {
        this.description = description; // Setter for description
    }
}