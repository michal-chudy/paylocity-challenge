package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Dependent;
import com.paylocity.benefitchallenge.domain.EmployeeSalary;
import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.domain.settings.BenefitDiscountSettings;
import com.paylocity.benefitchallenge.domain.settings.BenefitsSettings;
import com.paylocity.benefitchallenge.service.BenefitDeductionsService;
import com.paylocity.benefitchallenge.service.SettingsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.paylocity.benefitchallenge.domain.Benefit.BenefitType.DEPENDENT;
import static com.paylocity.benefitchallenge.domain.Benefit.BenefitType.EMPLOYEE;

@Service
@Transactional(readOnly = true)
public class BenefitDeductionsServiceImpl implements BenefitDeductionsService {

    @Inject
    private SettingsService settingsService;

    @Override
    public long calculateBenefitDeductionsForPayroll(Payroll payroll) {
        // the most recent employee salary is the current
        EmployeeSalary employeeSalary = payroll.getEmployee().getEmployeeSalary().get(0);

        return payroll.getPayrollBenefits().stream()
                .mapToLong(benefit -> calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount()))
                .sum();
    }

    @Override
    public long calculateBenefitDeduction(Benefit benefit, Integer annualPayrollCount) {
        BenefitsSettings benefitsSettings = settingsService.getBenefitsSettings();
        BenefitDiscountSettings benefitDiscountSettings = settingsService.getBenefitDiscountSettings();

        long benefitDeductionAmount;
        double benefitMultiplier = 1.0;
        Benefit.BenefitType benefitType = benefit.getBenefitType();
        Dependent dependent = benefit.getDependent();

        if (EMPLOYEE.equals(benefitType)) {
            benefitDeductionAmount = benefitsSettings.getAmountPerEmployee() / annualPayrollCount;
            if (benefit.getEmployee().getFirstName().startsWith(benefitDiscountSettings.getRuleNamePrefix())) {
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
