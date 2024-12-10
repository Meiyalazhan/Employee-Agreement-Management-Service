package com.gorai.employee.validation;

import com.gorai.employee.model.EmploymentAgreement;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, EmploymentAgreement> {

    @Override
    public boolean isValid(EmploymentAgreement agreement, ConstraintValidatorContext context) {
        if (agreement.getStartDate() == null || agreement.getEndDate() == null) {
            return true;
        }

        if (!agreement.getStartDate().isBefore(agreement.getEndDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Start date must be before end date")
                   .addPropertyNode("startDate")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}
