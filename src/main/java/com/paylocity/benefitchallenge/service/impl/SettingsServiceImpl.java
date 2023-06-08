package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.settings.BenefitDiscountSettings;
import com.paylocity.benefitchallenge.domain.settings.BenefitsSettings;
import com.paylocity.benefitchallenge.service.SettingsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SettingsServiceImpl implements SettingsService {

    private static final Long ANNUAL_AMOUNT_PER_EMPLOYEE = 100000L;
    private static final Long ANNUAL_AMOUNT_PER_DEPENDENT = 50000L;
    private static final double DEFAULT_DISCOUNT_PERCENTAGE = 0.1;
    private static final String DEFAULT_NAME_PREFIX_RULE = "A";

    private BenefitsSettings benefitsSettings;
    private BenefitDiscountSettings benefitDiscountSettings;

    /**
     * Initializes the settings singleton objects as per description
     */
    @PostConstruct
    protected void setUp() {
        benefitsSettings = new BenefitsSettings(ANNUAL_AMOUNT_PER_EMPLOYEE, ANNUAL_AMOUNT_PER_DEPENDENT);
        benefitDiscountSettings = new BenefitDiscountSettings(DEFAULT_DISCOUNT_PERCENTAGE, DEFAULT_NAME_PREFIX_RULE);
    }

    @Override
    public BenefitsSettings getBenefitsSettings() {
        return benefitsSettings;
    }

    @Override
    public BenefitDiscountSettings getBenefitDiscountSettings() {
        return benefitDiscountSettings;
    }
}
