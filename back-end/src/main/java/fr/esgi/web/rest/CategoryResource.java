package fr.esgi.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.CategoryService;
import fr.esgi.service.dto.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
     * @param wording the wording to search
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     * @throws HelpAndLearningPlatformException 
     */
    @ApiOperation(value = "Get all the categories by wording.")
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByWording(
            @RequestParam(name="wording", defaultValue = "") String wording
    ) throws HelpAndLearningPlatformException {
    	LOGGER.debug("REST request to find Categories by wording: {}", wording);
    	final List<CategoryDTO> categoriesDTO = categoryService.findAllByWording(wording);
        if (categoriesDTO.isEmpty()) {
           	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
        			"Pas de catégories");
        }
        return ResponseEntity.ok()
                .body(categoriesDTO);
    }

    /**
     * GET  /categories : get all the categories.
     * @param page the page number
     * @param size the number of elements
     *
     * @return the ResponseEntity with status 200 (OK) and the list of categories in body
     * @throws HelpAndLearningPlatformException 
     */
    @ApiOperation(value = "Get all the categories.")
    @GetMapping("/categories/all")
    public ResponseEntity<List<CategoryDTO>> getCategories() throws HelpAndLearningPlatformException {
        LOGGER.debug("REST request to find Categories");
    	final List<CategoryDTO> categoriesDTO = categoryService.findAll();
        if (categoriesDTO.isEmpty()) {
           	throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(), 
        			"Pas de catégories");
        }
        return ResponseEntity.ok()
                .body(categoryService.findAll());
    }
}
