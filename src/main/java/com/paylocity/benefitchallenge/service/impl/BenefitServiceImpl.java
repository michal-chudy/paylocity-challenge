package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Benefit;
import com.paylocity.benefitchallenge.domain.Employee;
import com.paylocity.benefitchallenge.domain.EmployeeSalary;
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
    private BenefitDeductionsService benefitDeductionsService;

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

        payrollService.addPayrollBenefit(benefit);

        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        BenefitDTO response = benefitMapper.benefitToBenefitDto(benefit);
        response.setDeductionAmountCents(benefitDeductionsService.calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount()));

        return response;
    }

    @Override
    public List<BenefitDTO> getAllByEmployee(Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        EmployeeSalary employeeSalary = employee.getEmployeeSalary().get(0);

        return benefitRepository.getAllByEmployeeId(employeeId).stream().map(benefit -> {
            BenefitDTO benefitDTO = benefitMapper.benefitToBenefitDto(benefit);
            benefitDTO.setDeductionAmountCents(benefitDeductionsService.calculateBenefitDeduction(benefit, employeeSalary.getAnnualPayrollCount()));
            return benefitDTO;
        }).collect(Collectors.toList());
    }

}
