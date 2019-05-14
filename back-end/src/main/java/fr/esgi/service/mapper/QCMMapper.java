package fr.esgi.service.mapper;

import fr.esgi.domain.QCM;
import fr.esgi.service.dto.QCMDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity QCM and its DTO called QCMDTO.
 * @author christopher
 */
@Mapper(uses = { QCMDTO.class }, componentModel = "spring")
public interface QCMMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "question", target = "question"),
    })
    QCMDTO qcmToQCMDTO(QCM qcm);

    @InheritInverseConfiguration
    QCM qcmDTOToQCM(QCMDTO qcmDTO);
}
