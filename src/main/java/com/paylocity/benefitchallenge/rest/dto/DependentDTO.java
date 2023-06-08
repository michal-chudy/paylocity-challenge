package com.paylocity.benefitchallenge.rest.dto;

import com.paylocity.benefitchallenge.domain.Dependent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DependentDTO {

    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private Long employeeId;
    @NotNull
    private Dependent.DependentType dependentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Dependent.DependentType getDependentType() {
        return dependentType;
    }

    public void setDependentType(Dependent.DependentType dependentType) {
        this.dependentType = dependentType;
    }
}
