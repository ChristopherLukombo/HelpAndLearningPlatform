package fr.esgi.service.mapper;

import fr.esgi.domain.Comment;
import fr.esgi.service.dto.CommentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity Comment and its DTO called CommentDTO.
 * @author christopher
 */
@Mapper(uses = { CommentDTO.class }, componentModel = "spring")
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "trick.id", target = "trickId"),
            @Mapping(source = "user.id", target = "userId"),
    })
    CommentDTO commentToCommentDTO(Comment comment);

    @InheritInverseConfiguration
    Comment CommentDTOToComment(CommentDTO commentDTO) ;
}
