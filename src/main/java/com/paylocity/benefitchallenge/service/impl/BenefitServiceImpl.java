package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Employee;
import com.paylocity.benefitchallenge.domain.EmployeeSalary;
import com.paylocity.benefitchallenge.domain.settings.BenefitDiscountSettings;
import com.paylocity.benefitchallenge.domain.settings.BenefitsSettings;
import com.paylocity.benefitchallenge.mapper.BenefitMapper;
import com.paylocity.benefitchallenge.repository.BenefitRepository;
import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;
import com.paylocity.benefitchallenge.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.dom.exception.InvalidStateException;

import javax.inject.Inject;

import static com.paylocity.benefitchallenge.domain.Benefit.BenefitType.DEPENDENT;
import static com.paylocity.benefitchallenge.domain.Benefit.BenefitType.EMPLOYEE;

@Service
@Transactional
public class BenefitServiceImpl implements BenefitService {

    @Inject
    private BenefitMapper benefitMapper;

    @Inject
    private DependentService dependentService;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private PayrollService payrollService;

    @Inject
    private SettingsService settingsService;

    @Inject
    private BenefitRepository benefitRepository;

    @Override
    public BenefitDTO createBenefit(BenefitDTO benefitDTO) {
        Benefit benefit = benefitMapper.benefitDtoToBenefit(benefitDTO);
        if (DEPENDENT.equals(benefitDTO.getBenefitType())) {
            benefit.setDependent(dependentService.getById(benefitDTO.getDependentId()));
        }
        benefit.setEmployee(employeeService.getById(benefitDTO.getEmployeeId()));
        benefit = benefitRepository.save(benefit);

        payrollService.addPayrollBenefit(benefit, calculateBenefitDeductionsInternal(benefit.getEmployee()));

        return benefitMapper.benefitToBenefitDto(benefit);
    }

    @Override
    public Long calculateBenefitDeductionsPerPayroll(Long employeeId) {
        return calculateBenefitDeductionsInternal(employeeService.getById(employeeId));
    }

    Long calculateBenefitDeductionsInternal(Employee employee) {
        BenefitsSettings benefitsSettings = settingsService.getBenefitsSettings();
        BenefitDiscountSettings benefitDiscountSettings = settingsService.getBenefitDiscountSettings();

        // the most recent employee salary is the current
        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        return benefitRepository.getAllByEmployeeId(employee.getId()).stream()
                .mapToLong(benefit -> {
                    long benefitDeductionAmount;
                    double benefitMultiplier = 1.0;

                    if (EMPLOYEE.equals(benefit.getBenefitType())) {
                        benefitDeductionAmount = benefitsSettings.getAmountPerEmployee() / employeeSalary.getAnnualPayrollCount();
                        if (employee.getFirstName().startsWith(benefitDiscountSettings.getRuleNamePrefix())) {
                            benefitMultiplier -= benefitDiscountSettings.getPercentage();
                        }
                    } else if (DEPENDENT.equals(benefit.getBenefitType())) {
                        benefitDeductionAmount = benefitsSettings.getAmountPerDependent() / employeeSalary.getAnnualPayrollCount();
                        if (benefit.getDependent().getFirstName().startsWith(benefitDiscountSettings.getRuleNamePrefix())) {
                            benefitMultiplier -= benefitDiscountSettings.getPercentage();
                        }
                    } else {
                        throw new InvalidStateException("Unknown benefit type: " + benefit.getBenefitType());
                    }

                    return (long) (benefitDeductionAmount * benefitMultiplier);
                })
                .sum();
    }
}
