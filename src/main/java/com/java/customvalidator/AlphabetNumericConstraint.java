/**
 * A-Z,a-z, 0-9, -,_
 * length : 3 -30
 */
package com.java.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlphabetNumericValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphabetNumericConstraint {

    String message() default "Invalid format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}