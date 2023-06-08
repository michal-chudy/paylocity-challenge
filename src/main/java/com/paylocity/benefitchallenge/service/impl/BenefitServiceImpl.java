package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Dependent;
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

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
        Employee employee = employeeService.getById(benefitDTO.getEmployeeId());
        benefit.setEmployee(employee);
        benefit = benefitRepository.save(benefit);

        payrollService.addPayrollBenefit(benefit, calculateBenefitDeductionsInternal(benefit.getEmployee()));

        BenefitsSettings benefitsSettings = settingsService.getBenefitsSettings();
        BenefitDiscountSettings benefitDiscountSettings = settingsService.getBenefitDiscountSettings();
        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        BenefitDTO response = benefitMapper.benefitToBenefitDto(benefit);
        response.setDeductionAmountCents(calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount(),
                benefitsSettings, benefitDiscountSettings, employee));

        return response;
    }

    @Override
    public Long calculateBenefitDeductionsPerPayroll(Long employeeId) {
        return calculateBenefitDeductionsInternal(employeeService.getById(employeeId));
    }

    @Override
    public List<BenefitDTO> getAllByEmployee(Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        return benefitRepository.getAllByEmployeeId(employeeId).stream().map(benefit -> {
            BenefitDTO benefitDTO = benefitMapper.benefitToBenefitDto(benefit);
            benefitDTO.setDeductionAmountCents(calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount(),
                    settingsService.getBenefitsSettings(), settingsService.getBenefitDiscountSettings(), employee));
            return benefitDTO;
        }).collect(Collectors.toList());
    }

    Long calculateBenefitDeductionsInternal(Employee employee) {
        BenefitsSettings benefitsSettings = settingsService.getBenefitsSettings();
        BenefitDiscountSettings benefitDiscountSettings = settingsService.getBenefitDiscountSettings();

        // the most recent employee salary is the current
        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        return benefitRepository.getAllByEmployeeId(employee.getId()).stream()
                .mapToLong(benefit -> calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount(),
                        benefitsSettings, benefitDiscountSettings, employee))
                .sum();
    }
    
    private long calculateBenefitDeduction(Benefit benefit, Integer annualPayrollCount, BenefitsSettings benefitsSettings,
                                           BenefitDiscountSettings benefitDiscountSettings, Employee employee) {
        long benefitDeductionAmount;
        double benefitMultiplier = 1.0;
        Benefit.BenefitType benefitType = benefit.getBenefitType();
        Dependent dependent = benefit.getDependent();

        if (EMPLOYEE.equals(benefitType)) {
            benefitDeductionAmount = benefitsSettings.getAmountPerEmployee() / annualPayrollCount;
            if (employee.getFirstName().startsWith(benefitDiscountSettings.getRuleNamePrefix())) {
                benefitMultiplier -= benefitDiscountSettings.getPercentage();
            }
        } else if (DEPENDENT.equals(benefitType)) {
            benefitDeductionAmount = benefitsSettings.getAmountPerDependent() / annualPayrollCount;
            if (dependent.getFirstName().startsWith(benefitDiscountSettings.getRuleNamePrefix())) {
                benefitMultiplier -= benefitDiscountSettings.getPercentage();
            }
        } else {
            throw new IllegalStateException("Unknown benefit type: " + benefitType);
        }

        return (long) (benefitDeductionAmount * benefitMultiplier);
    }
}
