package com.paylocity.benefitchallenge.domain.settings;

/**
 * This class would be normally an entity linked to a DB table
 */
public class BenefitsSettings {

    private Long amountPerEmployee;
    private Long amountPerDependent;

    public BenefitsSettings(Long amountPerEmployee, Long amountPerDependent) {
        this.amountPerEmployee = amountPerEmployee;
        this.amountPerDependent = amountPerDependent;
    }

    public Long getAmountPerEmployee() {
        return amountPerEmployee;
    }

    public Long getAmountPerDependent() {
        return amountPerDependent;
    }

}
