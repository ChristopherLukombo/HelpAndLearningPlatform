package fr.esgi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.CategoryRepository;
import fr.esgi.service.CategoryService;
import fr.esgi.service.dto.CategoryDTO;
import fr.esgi.service.mapper.CategoryMapper;

/**
 * Service Implementation for managing Category.
 */
@Service("CategoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private static final String PERCENTAGE = "%";

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Returns all Category by wording.
     *
     * @param wording to search
     * @return page of categories
     */
    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAllByWording(String wording) {
        LOGGER.debug("Request to find Categories by wording : {}", wording);
        return categoryRepository.findCategoriesByWording(PERCENTAGE + wording + PERCENTAGE).stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all Categories.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> findAll() {
        LOGGER.debug("Request to get all Categories");
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }
}
