package fr.esgi.service.impl;

import fr.esgi.dao.CategoryRepository;
import fr.esgi.service.CategoryService;
import fr.esgi.service.dto.CategoryDTO;
import fr.esgi.service.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("CategoryService")
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
     * @param wording
     * @return page of categories
     */
    @Transactional(readOnly = true)
    @Override
    public Page<CategoryDTO> findCategoriesByWording(int page, int size, String wording) {
        LOGGER.debug("Find Categories by wording : {}", wording);
        return categoryRepository.findCategoriesByWording(PERCENTAGE + wording + PERCENTAGE, PageRequest.of(page, size))
                .map(categoryMapper::categoryToCategoryDTO);
    }

    /**
     * Get all Categories.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    @Override
    public Page<CategoryDTO> findAll(int page, int size) {
        LOGGER.debug("Request to get all Categories");
        return categoryRepository.findAll(PageRequest.of(page, size))
                .map(categoryMapper::categoryToCategoryDTO);
    }
}
