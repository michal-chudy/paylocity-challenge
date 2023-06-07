package com.paylocity.benefitchallenge.mapper;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BenefitMapper {

    Benefit benefitDtoToBenefit(BenefitDTO benefitDTO);

    @Mappings({
            @Mapping(target = "employeeId", source = "employee.id"),
            @Mapping(target = "dependentId", source = "dependent.id")
    })
    BenefitDTO benefitToBenefitDto(Benefit benefit);
}
