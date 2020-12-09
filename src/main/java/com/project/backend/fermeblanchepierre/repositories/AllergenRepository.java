package com.project.backend.fermeblanchepierre.repositories;

import com.project.backend.fermeblanchepierre.entities.Allergen;
import org.springframework.data.repository.CrudRepository;

public interface AllergenRepository extends CrudRepository<Allergen, Integer> {
}
