package com.paylocity.benefitchallenge.rest;

import com.paylocity.benefitchallenge.rest.dto.DependentDTO;
import com.paylocity.benefitchallenge.service.DependentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/dependents")
@Tag(name = "Dependents Resource")
public class DependentResource {

    @Inject
    private DependentService dependentService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @Operation(description = "Creates a new Dependent")
    public ResponseEntity<DependentDTO> createDependent(@Valid @RequestBody DependentDTO dependentDTO) {
        return ResponseEntity.ok(dependentService.createDependent(dependentDTO));
    }
}
