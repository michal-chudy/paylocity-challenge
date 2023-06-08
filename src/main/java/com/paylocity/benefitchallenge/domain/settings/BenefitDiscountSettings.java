package com.paylocity.benefitchallenge.domain.settings;

/**
 * This class would be normally an entity linked to a DB table
 */
public class BenefitDiscountSettings {

    private Double percentage;
    private String ruleNamePrefix;

    public BenefitDiscountSettings(Double percentage, String ruleNamePrefix) {
        this.percentage = percentage;
        this.ruleNamePrefix = ruleNamePrefix;
    }

    public Double getPercentage() {
        return percentage;
    }

    public String getRuleNamePrefix() {
        return ruleNamePrefix;
    }
}
