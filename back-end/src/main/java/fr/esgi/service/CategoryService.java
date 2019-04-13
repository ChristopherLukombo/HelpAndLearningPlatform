package fr.esgi.service;

import fr.esgi.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    /**
     * Returns all Category by wording.
     * @param wording
     * @return page of categories
     */
    Page<CategoryDTO> findCategoriesByWording(int page, int size, String wording);

    /**
     * Get all Categories.
     *
     * @return the list of entities
     */
    Page<CategoryDTO> findAll(int page, int size);
}
