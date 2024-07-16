package sn.ads.couturepro.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class TelephoneValidator implements ConstraintValidator<ValidTelephone, String> {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(77|78|70|75|76)[0-9]{7}$");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && PHONE_PATTERN.matcher(value).matches();
    }

}