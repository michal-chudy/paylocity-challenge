package com.paylocity.benefitchallenge.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table
@Entity(name = "benefit_discount")
public class BenefitDiscount {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    private Benefit benefit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BenefitDiscount that = (BenefitDiscount) o;
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
                .append("percentage", percentage)
                .append("benefit", benefit)
                .toString();
    }
}
