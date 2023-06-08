package com.paylocity.benefitchallenge.repository;

import com.paylocity.benefitchallenge.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> getAllByEmployeeIdAndPayrollStatus(Long employeeId, Payroll.PayrollStatus payrollStatus);
}
