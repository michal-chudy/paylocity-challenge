package com.paylocity.benefitchallenge.domain.settings;

/**
 * This class would be normally an entity linked to a DB table
 */
public class BenefitDiscountSettings {

    private Integer percentage;
    private String ruleNamePrefix;

    public BenefitDiscountSettings(Integer percentage, String ruleNamePrefix) {
        this.percentage = percentage;
        this.ruleNamePrefix = ruleNamePrefix;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getRuleNamePrefix() {
        return ruleNamePrefix;
    }
}
