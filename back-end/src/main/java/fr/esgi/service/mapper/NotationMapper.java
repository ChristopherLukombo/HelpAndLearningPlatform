package fr.esgi.service.mapper;

import fr.esgi.domain.Notation;
import fr.esgi.service.dto.NotationDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity Notation and its DTO called NotationDTO.
 * @author christopher
 */
@Mapper(uses = { NotationDTO.class }, componentModel = "spring")
public interface NotationMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "note", target = "note"),
            @Mapping(source = "trick.id", target = "trickId"),
    })
    NotationDTO notationToNotationDTO(Notation notation);

    @InheritInverseConfiguration
    Notation notationDTOToNotation(NotationDTO notationDTO);
}
