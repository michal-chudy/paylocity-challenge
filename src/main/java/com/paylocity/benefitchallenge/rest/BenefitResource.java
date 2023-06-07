package com.paylocity.benefitchallenge.rest;

import com.paylocity.benefitchallenge.rest.dto.BenefitDTO;
import com.paylocity.benefitchallenge.service.BenefitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

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
}
