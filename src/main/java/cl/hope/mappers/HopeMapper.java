package cl.hope.mappers;

import cl.hope.controllers.dtos.HopeRequest;
import cl.hope.controllers.dtos.HopeResponse;
import cl.hope.repositories.entities.HopeEntity;
import cl.hope.services.entities.Hope;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HopeMapper {

    HopeEntity hopeToHopeEntity(Hope hope);

    Hope hopeRequestToHope(HopeRequest hopeRequest);

    Hope hopeEntityToHope(HopeEntity hopeEntity);

    HopeResponse hopeToHopeResponse(Hope hope);

    List<Hope> listHopeEntityToListHope(List<HopeEntity> hopeEntityList);

    List<HopeResponse> listHopeToListHopeResponse(List<Hope> hopeList);

}
