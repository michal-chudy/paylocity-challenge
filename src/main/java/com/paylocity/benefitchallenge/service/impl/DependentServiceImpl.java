package com.paylocity.benefitchallenge.service.impl;

import com.paylocity.benefitchallenge.domain.Dependent;
import com.paylocity.benefitchallenge.mapper.DependentMapper;
import com.paylocity.benefitchallenge.repository.DependentRepository;
import com.paylocity.benefitchallenge.rest.dto.DependentDTO;
import com.paylocity.benefitchallenge.service.DependentService;
import com.paylocity.benefitchallenge.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class DependentServiceImpl implements DependentService {

    @Inject
    private DependentRepository dependentRepository;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private DependentMapper dependentMapper;

    @Override
    public DependentDTO createDependent(DependentDTO dependentDTO) {
        Dependent dependent = dependentMapper.dependentDtoToDependent(dependentDTO);
        dependent.setEmployee(employeeService.getById(dependentDTO.getEmployeeId()));
        return dependentMapper.dependentToDependentDto(dependentRepository.save(dependent));
    }

    @Override
    @Transactional(readOnly = true)
    public Dependent getById(Long id) {
        return dependentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dependent not found"));
    }
}
