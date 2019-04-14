package fr.esgi.service.mapper;

import fr.esgi.domain.QCMAnswers;
import fr.esgi.service.dto.QCMAnswersDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity QCMAnswers and its DTO called QCMAnswersDTO.
 * @author christopher
 */
@Mapper(uses = { QCMAnswersDTO.class }, componentModel = "spring")
public interface QCMAnswersMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "qcm.id", target = "qcmId"),
            @Mapping(source = "answer", target = "answer"),
            @Mapping(source = "trick.id", target = "trickId"),
    })
    QCMAnswersDTO qcmanswersToQcmanswersDTO(QCMAnswers qcmAnswers);

    @InheritInverseConfiguration
    QCMAnswers qcmanswersDTOToqcmanswers(QCMAnswersDTO qcmanswersDTO);
}
