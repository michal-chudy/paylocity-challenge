package com.paylocity.benefitchallenge.repository;

import com.paylocity.benefitchallenge.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Optional<Payroll> getByEmployeeIdAndPayrollStatus(Long employeeId, Payroll.PayrollStatus payrollStatus);
}
