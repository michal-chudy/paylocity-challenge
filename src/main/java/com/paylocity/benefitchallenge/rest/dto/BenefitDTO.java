package com.paylocity.benefitchallenge.rest.dto;

import com.paylocity.benefitchallenge.domain.Benefit;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BenefitDTO {

    private Long id;

    @NotNull
    private Long employeeId;
    @NotNull
    private Benefit.BenefitType benefitType;
    private Long dependentId;

    private Long deductionAmountCents;
    private BigDecimal deductionAmountDollars;

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

    public Long getDeductionAmountCents() {
        return deductionAmountCents;
    }

    public void setDeductionAmountCents(Long deductionAmountCents) {
        this.deductionAmountCents = deductionAmountCents;
        this.deductionAmountDollars = new BigDecimal(deductionAmountCents).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDeductionAmountDollars() {
        return deductionAmountDollars;
    }

    public void setDeductionAmountDollars(BigDecimal deductionAmountDollars) {
        this.deductionAmountDollars = deductionAmountDollars;
    }
}
