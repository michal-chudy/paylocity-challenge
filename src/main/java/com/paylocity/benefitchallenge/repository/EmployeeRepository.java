package com.paylocity.benefitchallenge.repository;

import com.paylocity.benefitchallenge.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
