package sn.ads.couturepro.Validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CiviliteValidator  implements ConstraintValidator<ValidCivilite, String> {

    private static final Pattern CIVILITE_PATTERN = Pattern.compile("^(M|Mme)$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && CIVILITE_PATTERN.matcher(value).matches();
    }
}
