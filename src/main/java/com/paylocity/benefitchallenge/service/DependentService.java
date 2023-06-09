package com.paylocity.benefitchallenge.service;

import com.paylocity.benefitchallenge.domain.Dependent;
import com.paylocity.benefitchallenge.rest.dto.DependentDTO;

public interface DependentService {

    /**
     * Creates a new dependent
     * @param dependentDTO
     * @return
     */
    DependentDTO createDependent(DependentDTO dependentDTO);

    Dependent getById(Long id);
}
