package fr.esgi.service.mapper;

import fr.esgi.domain.Category;
import fr.esgi.service.dto.CategoryDTO;

/**
 * Mapper for the entity Category and its DTO called CategoryDTO.
 * @author christopher
 */
public class CategoryMapperDecorator implements CategoryMapper {

    public CategoryDTO categoryToCategoryDTO(Category category) {
        return new CategoryDTO(category);
    }

    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        } else {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setWording(categoryDTO.getWording());
            return category;
        }
    }

}
