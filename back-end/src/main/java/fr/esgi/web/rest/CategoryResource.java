package fr.esgi.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.service.CategoryService;
import fr.esgi.service.dto.CategoryDTO;
import io.swagger.annotations.Api;

/**
 * REST controller for managing Category.
 * @author christopher
 */
@Api(value = "Category")
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResource.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * GET  /categories : get all the categories by wording.
     * @param page the page number
     * @param size the number of elements
     * @param wording the wording to search
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     */
    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryDTO>> findCategoriesByWording(
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size,
            @RequestParam(name="wording", defaultValue = "") String wording
    ) {
        LOGGER.debug("REST request to find Categories by wording: {}", wording);
        return ResponseEntity.ok()
                .body(categoryService.findCategoriesByWording(page, size, wording));
    }

    /**
     * GET  /categories : get all the categories.
     * @param page the page number
     * @param size the number of elements
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     */
    @GetMapping("/categories/all")
    public ResponseEntity<Page<CategoryDTO>> findCategories(
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size
    ) {
        LOGGER.debug("REST request to find Categories");
        return ResponseEntity.ok()
                .body(categoryService.findAll(page, size));
    }
}
