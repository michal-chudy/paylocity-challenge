package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Payroll;

public interface BenefitDeductionsService {

    long calculateBenefitDeductionsForPayroll(Payroll payroll);

    long calculateBenefitDeduction(Benefit benefit, Integer annualPayrollCount);
}
