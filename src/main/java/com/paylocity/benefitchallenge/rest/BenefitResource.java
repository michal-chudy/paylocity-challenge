package com.paylocity.benefitchallenge.rest;

import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;
import com.paylocity.benefitchallenge.service.BenefitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/benefit")
public class BenefitResource {

    @Inject
    private BenefitService benefitService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BenefitDTO> createBenefit(@Valid @RequestBody BenefitDTO benefitDTO) {
        return ResponseEntity.ok(benefitService.createBenefit(benefitDTO));
    }

    @GetMapping(path = "/employee/{employeeId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BenefitDTO>> getBenefitsByEmployee(@PathVariable("employeeId") Long employeeId) {
        return ResponseEntity.ok(benefitService.getAllByEmployee(employeeId));
    }
}
