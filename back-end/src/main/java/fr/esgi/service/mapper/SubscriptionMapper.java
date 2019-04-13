package fr.esgi.service.mapper;

import fr.esgi.domain.Subscription;
import fr.esgi.service.dto.SubscriptionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity Subscription and its DTO called SubscriptionDTO.
 * @author christopher
 */
@Mapper(uses = { SubscriptionDTO.class }, componentModel = "spring")
public interface SubscriptionMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "category.id", target = "categoryId"),
            @Mapping(source = "subscriptionDate", target = "subscriptionDate"),
            @Mapping(source = "user.id", target = "userId"),
    })
    SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription);

    @InheritInverseConfiguration
    Subscription subscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO) ;
}
