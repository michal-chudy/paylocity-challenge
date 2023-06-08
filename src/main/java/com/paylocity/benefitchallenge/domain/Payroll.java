package com.paylocity.benefitchallenge.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table
@Entity(name = "payroll")
public class Payroll {

    public enum PayrollStatus {
        DRAFT, COMPLETED
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(name = "payroll_date")
    private ZonedDateTime payrollDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payroll_status", nullable = false)
    private PayrollStatus payrollStatus = PayrollStatus.DRAFT;

    @Column(name = "gross_amount")
    private Long grossAmount;

    @Column(name = "benefit_deductions")
    private Long benefitDeductions;

    @Column(name = "net_amount")
    private Long netAmount;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_salary_id", referencedColumnName = "id")
    private EmployeeSalary employeeSalary;

    @ManyToMany
    @JoinTable(name = "payroll_benefits", joinColumns = {
            @JoinColumn(name = "payroll_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    })
    private List<Benefit> payrollBenefits;

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

    public PayrollStatus getPayrollStatus() {
        return payrollStatus;
    }

    public void setPayrollStatus(PayrollStatus payrollStatus) {
        this.payrollStatus = payrollStatus;
    }

    public Long getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(Long grossAmount) {
        this.grossAmount = grossAmount;
    }

    public Long getBenefitDeductions() {
        return benefitDeductions;
    }

    public void setBenefitDeductions(Long benefitDeductions) {
        this.benefitDeductions = benefitDeductions;
    }

    public Long getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeSalary getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(EmployeeSalary employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public List<Benefit> getPayrollBenefits() {
        return payrollBenefits;
    }

    public void setPayrollBenefits(List<Benefit> payrollBenefits) {
        this.payrollBenefits = payrollBenefits;
    }

    public void addPayrollBenefit(Benefit benefit) {
        if (payrollBenefits == null) {
            payrollBenefits = new ArrayList<>();
        }
        payrollBenefits.add(benefit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payroll payroll = (Payroll) o;
        return id.equals(payroll.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("payrollDate", payrollDate)
                .append("payrollStatus", payrollStatus)
                .append("grossAmount", grossAmount)
                .append("benefitDeductions", benefitDeductions)
                .append("netAmount", netAmount)
                .append("employee", employee)
                .append("employeeSalary", employeeSalary)
                .append("payrollBenefits", payrollBenefits)
                .toString();
    }
}
