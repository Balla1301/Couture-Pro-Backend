package sn.ads.couturepro.Validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CiviliteValidator.class)
public @interface ValidCivilite {
    String message() default "La civilité doit être 'M' ou 'Mme'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
