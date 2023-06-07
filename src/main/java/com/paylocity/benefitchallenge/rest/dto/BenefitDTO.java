package com.paylocity.benefitchallenge.rest.dto;

import com.paylocity.benefitchallenge.domain.Benefit;

import javax.validation.constraints.NotNull;

public class BenefitDTO {

    private Long id;

    @NotNull
    private Long employeeId;
    @NotNull
    private Benefit.BenefitType benefitType;
    private Long dependentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Benefit.BenefitType getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(Benefit.BenefitType benefitType) {
        this.benefitType = benefitType;
    }

    public Long getDependentId() {
        return dependentId;
    }

    public void setDependentId(Long dependentId) {
        this.dependentId = dependentId;
    }
}
