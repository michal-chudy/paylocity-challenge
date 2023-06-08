package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Employee;
import com.paylocity.benefitchallenge.repository.EmployeeRepository;
import com.paylocity.benefitchallenge.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }
}
