package fr.esgi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.esgi.service.dto.CategoryDTO;

/**
 * Service Implementation for managing Category.
 */
@Service
public interface CategoryService {

    /**
     * Returns all Category by wording.
     * @return the list of categories
     */
    List<CategoryDTO> findAllByWording(String wording);

    /**
     * Get all Categories.
     *
     * @return the list of entities
     */
    List<CategoryDTO> findAll();
}
