package fr.esgi.service.mapper;

import fr.esgi.domain.Category;
import fr.esgi.service.dto.CategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity Category and its DTO called CategoryDTO.
 * @author christopher
 */
@Mapper(uses = { CategoryDTO.class }, componentModel = "spring")
@DecoratedWith(CategoryMapperDecorator.class)
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "wording", target = "wording"),
    })
    CategoryDTO categoryToCategoryDTO(Category category);

    @InheritInverseConfiguration
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
