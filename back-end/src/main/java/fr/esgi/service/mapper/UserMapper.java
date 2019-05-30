package fr.esgi.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import fr.esgi.domain.User;
import fr.esgi.service.dto.UserDTO;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 * @author christopher
 */
@Mapper(uses = { UserDTO.class }, componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "countryOfResidence", target = "countryOfResidence"),
            @Mapping(source = "activated", target = "activated"),
            @Mapping(source = "langKey", target = "langKey"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "authority.id", target = "authorityId"),
    })
    UserDTO userToUserDTO(User user);

    @InheritInverseConfiguration
    User userDTOToUser(UserDTO userDTO);
}
