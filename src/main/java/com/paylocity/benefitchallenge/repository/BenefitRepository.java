package com.paylocity.benefitchallenge.repository;

import com.paylocity.benefitchallenge.domain.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    List<Benefit> getAllByEmployeeId(Long employeeId);
}
