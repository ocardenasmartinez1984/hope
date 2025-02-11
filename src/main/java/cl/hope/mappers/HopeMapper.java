package cl.hope.mappers;

import cl.hope.services.entities.Hope;
import cl.hope.repositories.entities.HopeEntity;
import cl.hope.entities.HopeRequest;
import cl.hope.entities.HopeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HopeMapper {

    HopeMapper INSTANCE = Mappers.getMapper(HopeMapper.class);

    HopeEntity hopeToHopeEntity(Hope hope);

    Hope hopeRequestToHope(HopeRequest hopeRequest);

    List<Hope> listHopeEntityToListHope(List<HopeEntity> hopeEntityList);

    List<HopeResponse> listHopeToListHopeResponse(List<Hope> hopeList);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd/MM/yyyy")
    HopeResponse hopeToHopeResponse(Hope hope);

    Hope hopeEntityTityToHope(HopeEntity hopeEntity);

}
