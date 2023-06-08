package com.paylocity.benefitchallenge.mapper;

import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.rest.dto.PayrollDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PayrollMapper {

    @Mappings({
            @Mapping(target = "employeeId", source = "employee.id"),
            @Mapping(target = "employeeSalaryId", source = "employeeSalary.id")
    })
    PayrollDTO payrollToPayrollDTO(Payroll payroll);
}
