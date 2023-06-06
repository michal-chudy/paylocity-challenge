package com.paylocity.benefitchallenge.mapper;

import com.paylocity.benefitchallenge.domain.Dependent;
import com.paylocity.benefitchallenge.rest.dto.DependentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DependentMapper {

    Dependent dependentDtoToDependent(DependentDTO dependentDTO);

    @Mappings({
            @Mapping(target = "employeeId", source = "employee.id")
    })
    DependentDTO dependentToDependentDto(Dependent dependent);
}
