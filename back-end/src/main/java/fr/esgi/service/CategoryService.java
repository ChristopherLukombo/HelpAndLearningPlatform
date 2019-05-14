package fr.esgi.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import fr.esgi.service.dto.CategoryDTO;

/**
 * Service Implementation for managing Category.
 */
@Service
public interface CategoryService {

    /**
     * Returns all Category by wording.
     * @param page 
     * @param size 
     * @param wording
     * @return page of categories
     */
    Page<CategoryDTO> findCategoriesByWording(int page, int size, String wording);

    /**
     * Get all Categories.
     * @param page 
     * @param size 
     *
     * @return the list of entities
     */
    Page<CategoryDTO> findAll(int page, int size);
}
