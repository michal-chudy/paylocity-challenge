package com.paylocity.benefitchallenge.rest;

import com.paylocity.benefitchallenge.domain.Payroll;
import com.paylocity.benefitchallenge.rest.dto.PayrollDTO;
import com.paylocity.benefitchallenge.service.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/payrolls")
@Tag(name = "Payroll resource")
public class PayrollResource {

    @Inject
    private PayrollService payrollService;

    @GetMapping(path = "/employee/{employeeId}")
    @Operation(description = "Retrieves all Payroll objects in the specified payrollStatus for an Employee with the specified employeeId")
    public ResponseEntity<List<PayrollDTO>> getPayrollForEmployee(@PathVariable("employeeId") Long employeeId,
                                                                  @RequestParam("payrollStatus")Payroll.PayrollStatus payrollStatus) {
        return ResponseEntity.ok(payrollService.getPayrollsForEmployee(employeeId, payrollStatus));
    }

}
