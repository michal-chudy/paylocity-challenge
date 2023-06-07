package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;

public interface BenefitService {

    BenefitDTO createBenefit(BenefitDTO benefitDTO);

    Long calculateBenefitDeductionsPerPayroll(Long employeeId);
}
