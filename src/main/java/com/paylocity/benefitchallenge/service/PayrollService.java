package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Payroll;

public interface PayrollService {

    Payroll addPayrollBenefit(Benefit benefit, Long totalBenefitDeductions);

    Payroll getDraftPayrollByEmployee(Long employeeId);
}
