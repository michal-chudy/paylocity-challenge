package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;

import java.util.List;

public interface BenefitService {

    BenefitDTO createBenefit(BenefitDTO benefitDTO);

    List<BenefitDTO> getAllByEmployee(Long emplyeeId);
}
