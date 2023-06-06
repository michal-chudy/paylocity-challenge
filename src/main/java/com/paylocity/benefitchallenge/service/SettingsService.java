package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.domain.settings.BenefitDiscountSettings;
import com.paylocity.benefitchallenge.domain.settings.BenefitsSettings;

public interface SettingsService {

    BenefitsSettings getBenefitsSettings();

    BenefitDiscountSettings getBenefitDiscountSettings();
}
