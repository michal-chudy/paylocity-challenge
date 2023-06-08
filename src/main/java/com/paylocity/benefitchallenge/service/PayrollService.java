package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.rest.dto.PayrollDTO;

import java.util.List;

public interface PayrollService {

    Payroll addPayrollBenefit(Benefit benefit, Long totalBenefitDeductions);

    List<PayrollDTO> getPayrollsForEmployee(Long employeeId, Payroll.PayrollStatus payrollStatus);
}
