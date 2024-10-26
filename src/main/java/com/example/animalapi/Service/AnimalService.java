package com.example.animalapi.Service;

import com.example.animalapi.model.Animal;
import com.example.animalapi.repository.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {
        logger.info("Fetching animal with id: {}", id);
        return animalRepository.findById(id);
    }

    public Animal addAnimal(Animal animal) {
        logger.info("Adding animal: {}", animal);
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(int id, Animal animalDetails) {
        logger.info("Updating animal with id: {}", id);

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));

        animal.setName(animalDetails.getName());
        animal.setScientificName(animalDetails.getScientificName());
        animal.setSpecies(animalDetails.getSpecies());
        animal.setHabitat(animalDetails.getHabitat());
        animal.setDescription(animalDetails.getDescription());

        return animalRepository.save(animal);
    }

    public void deleteAnimal(int id) {
        logger.info("Deleting animal with id: {}", id);
        animalRepository.deleteById(id);
    }

    public List<Animal> getAnimalsBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }

    public List<Animal> searchAnimalsByName(String name) {
        return animalRepository.findByNameContaining(name);
    }
}