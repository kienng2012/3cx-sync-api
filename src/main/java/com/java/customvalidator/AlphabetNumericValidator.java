package com.java.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlphabetNumericValidator implements ConstraintValidator<AlphabetNumericConstraint, String> {

    @Override
    public void initialize(AlphabetNumericConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9A-Za-z]+")
                && (contactField.length() > 3) && (contactField.length() < 30); //contactField.matches("[0-9A-Za-z_-]+")
    }

}
