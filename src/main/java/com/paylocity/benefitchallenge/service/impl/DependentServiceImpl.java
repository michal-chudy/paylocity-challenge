package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Dependent;
import com.paylocity.benefitchallenge.mapper.DependentMapper;
import com.paylocity.benefitchallenge.repository.DependentRepository;
import com.paylocity.benefitchallenge.repository.EmployeeRepository;
import com.paylocity.benefitchallenge.rest.dto.DependentDTO;
import com.paylocity.benefitchallenge.service.DependentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
@Transactional
public class DependentServiceImpl implements DependentService {

    @Inject
    private DependentRepository dependentRepository;

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private DependentMapper dependentMapper;

    @Override
    public DependentDTO createDependent(DependentDTO dependentDTO) {
        Dependent dependent = dependentMapper.dependentDtoToDependent(dependentDTO);
        dependent.setEmployee(employeeRepository.findById(dependentDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID")));
        return dependentMapper.dependentToDependentDto(dependentRepository.save(dependent));
    }
}
