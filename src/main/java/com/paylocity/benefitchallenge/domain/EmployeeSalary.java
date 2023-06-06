package com.paylocity.benefitchallenge.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table
@Entity(name = "employee_salary")
public class EmployeeSalary {

    /**
     * Default number of payrolls per year
     */
    private static final int ANNUAL_PAYROLL_COUNT = 26;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Long amount;

    @NotNull
    @Column(name = "annual_payroll_count", nullable = false)
    private Integer annualPayrollCount = ANNUAL_PAYROLL_COUNT;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getAnnualPayrollCount() {
        return annualPayrollCount;
    }

    public void setAnnualPayrollCount(Integer annualPayrollCount) {
        this.annualPayrollCount = annualPayrollCount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeSalary that = (EmployeeSalary) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("amount", amount)
                .append("annualPayrollCount", annualPayrollCount)
                .append("employee", employee)
                .toString();
    }
}
