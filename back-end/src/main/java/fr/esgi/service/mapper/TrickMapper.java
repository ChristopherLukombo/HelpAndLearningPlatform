package fr.esgi.service.mapper;

import fr.esgi.domain.Trick;
import fr.esgi.service.dto.TrickDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity Trick and its DTO called TrickDTO.
 * @author christopher
 */
@Mapper(uses = { TrickDTO.class }, componentModel = "spring")
public interface TrickMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "wording", target = "wording"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "category.id", target = "categoryId"),
            @Mapping(source = "ownUser.id", target = "ownUserId"),
            @Mapping(source = "creationDate", target = "creationDate"),
    })
    TrickDTO trickToTrickDTO(Trick trick);

    @InheritInverseConfiguration
    Trick trickDTOToTrick(TrickDTO trickDTO);
}
