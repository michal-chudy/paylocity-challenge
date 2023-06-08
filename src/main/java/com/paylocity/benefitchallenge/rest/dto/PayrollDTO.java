package com.paylocity.benefitchallenge.rest.dto;

import com.paylocity.benefitchallenge.CurrencyUtils;
import com.paylocity.benefitchallenge.domain.Payroll;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PayrollDTO {

    private Long id;

    private ZonedDateTime payrollDate;
    private Payroll.PayrollStatus payrollStatus;
    private Long grossAmount;
    private BigDecimal grossAmountDollars;
    private Long benefitDeductions;
    private BigDecimal benefitDeductionsDollars;
    private Long netAmount;
    private BigDecimal netAmountDollars;
    private Long employeeId;
    private Long employeeSalaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getPayrollDate() {
        return payrollDate;
    }

    public void setPayrollDate(ZonedDateTime payrollDate) {
        this.payrollDate = payrollDate;
    }

    public Payroll.PayrollStatus getPayrollStatus() {
        return payrollStatus;
    }

    public void setPayrollStatus(Payroll.PayrollStatus payrollStatus) {
        this.payrollStatus = payrollStatus;
    }

    public Long getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Long grossAmount) {
        this.grossAmount = grossAmount;
        this.grossAmountDollars = CurrencyUtils.getDollarValue(grossAmount);
    }

    public BigDecimal getGrossAmountDollars() {
        return grossAmountDollars;
    }

    public Long getBenefitDeductions() {
        return benefitDeductions;
    }

    public void setBenefitDeductions(Long benefitDeductions) {
        this.benefitDeductions = benefitDeductions;
        this.benefitDeductionsDollars = CurrencyUtils.getDollarValue(benefitDeductions);
    }

    public BigDecimal getBenefitDeductionsDollars() {
        return benefitDeductionsDollars;
    }

    public Long getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
        this.netAmountDollars = CurrencyUtils.getDollarValue(netAmount);
    }

    public BigDecimal getNetAmountDollars() {
        return netAmountDollars;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeSalaryId() {
        return employeeSalaryId;
    }

    public void setEmployeeSalaryId(Long employeeSalaryId) {
        this.employeeSalaryId = employeeSalaryId;
    }
}
