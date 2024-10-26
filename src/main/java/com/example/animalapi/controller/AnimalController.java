package com.example.animalapi.controller;

import com.example.animalapi.model.Animal;
import com.example.animalapi.Service.AnimalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/animals")
public class AnimalController {

    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    private AnimalService animalService;

    @GetMapping("/all")
    public String getAllAnimals(Model model) {
        model.addAttribute("animals", animalService.getAllAnimals());
        return "animal-list"; // Thymeleaf template for listing animals
    }

    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable int id, Model model) {
        logger.info("Fetching animal with id: {}", id);
        Optional<Animal> animalOptional = animalService.getAnimalById(id);
        if (animalOptional.isPresent()) {
            model.addAttribute("animal", animalOptional.get());
            return "animal-details"; // Thymeleaf template for a single animal
        }
        logger.warn("Animal not found with id: {}", id);
        model.addAttribute("errorMessage", "Animal not found");
        return "404"; // Return a 404 template if not found
    }

    @PostMapping
    public String addAnimal(@ModelAttribute Animal animal) {
        animalService.addAnimal(animal);
        return "redirect:/api/animals/all"; // Redirect to the list after adding
    }

    @PostMapping("/update/{id}")
    public String updateAnimal(@PathVariable int id, @ModelAttribute Animal animal) {
        animalService.updateAnimal(id, animal);
        return "redirect:/api/animals/all"; // Redirect after updating
    }

    @DeleteMapping("/{id}")
    public String deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return "redirect:/api/animals/all"; // Redirect after deleting
    }

    @GetMapping("/species/{species}")
    public String getAnimalsBySpecies(@PathVariable String species, Model model) {
        List<Animal> animals = animalService.getAnimalsBySpecies(species);
        model.addAttribute("animals", animals);
        return "animal-list"; // Return the name of the Thymeleaf template
    }

    @GetMapping("/search")
    public String searchAnimals(@RequestParam String name, Model model) {
        List<Animal> animals = animalService.searchAnimalsByName(name);
        model.addAttribute("animals", animals);
        return "animal-list"; // Return the name of the Thymeleaf template
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("animal", new Animal());
        return "animal-create"; // Thymeleaf template for creating an animal
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Animal animal = animalService.getAnimalById(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        model.addAttribute("animal", animal);
        return "animal-update"; // Thymeleaf template for updating an animal
    }

    // Error handling for unexpected exceptions
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error"; // A template for error display
    }
}