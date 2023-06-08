package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.mapper.PayrollMapper;
import com.paylocity.benefitchallenge.repository.PayrollRepository;
import com.paylocity.benefitchallenge.rest.dto.PayrollDTO;
import com.paylocity.benefitchallenge.service.PayrollService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    @Inject
    private PayrollMapper payrollMapper;

    @Inject
    private PayrollRepository payrollRepository;

    @Override
    public Payroll addPayrollBenefit(Benefit benefit, Long totalBenefitDeductions) {
        return payrollRepository.getAllByEmployeeIdAndPayrollStatus(benefit.getEmployee().getId(), Payroll.PayrollStatus.DRAFT).stream()
                .findFirst()
                .map(payroll -> {
                    payroll.addPayrollBenefit(benefit);
                    payroll.setBenefitDeductions(totalBenefitDeductions);
                    payroll.setNetAmount(payroll.getGrossAmount() - totalBenefitDeductions);
                    return payrollRepository.save(payroll);
                }).orElseThrow(() -> new EntityNotFoundException("DRAFT payroll not found"));
    }

    @Override
    public List<PayrollDTO> getPayrollsForEmployee(Long employeeId, Payroll.PayrollStatus payrollStatus) {
        return payrollRepository.getAllByEmployeeIdAndPayrollStatus(employeeId, payrollStatus).stream()
                .map(payrollMapper::payrollToPayrollDTO)
                .collect(Collectors.toList());
    }
}
