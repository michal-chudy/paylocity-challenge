package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.repository.PayrollRepository;
import com.paylocity.benefitchallenge.service.PayrollService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Inject
    private PayrollRepository payrollRepository;

    @Override
    public Payroll addPayrollBenefit(Benefit benefit, Long totalBenefitDeductions) {
        return payrollRepository.getByEmployeeIdAndPayrollStatus(benefit.getEmployee().getId(), Payroll.PayrollStatus.DRAFT)
                .map(payroll -> {
                    payroll.addPayrollBenefit(benefit);
                    payroll.setBenefitDeductions(totalBenefitDeductions);
                    payroll.setNetAmount(payroll.getGrossAmount() - totalBenefitDeductions);
                    return payrollRepository.save(payroll);
                }).orElseThrow(() -> new EntityNotFoundException("DRAFT payroll not found"));
    }

    @Override
    public Payroll getDraftPayrollByEmployee(Long employeeId) {
        return payrollRepository.getByEmployeeIdAndPayrollStatus(employeeId, Payroll.PayrollStatus.DRAFT)
                .orElseThrow(() -> new EntityNotFoundException("DRAFT payroll not found"));
    }
}
