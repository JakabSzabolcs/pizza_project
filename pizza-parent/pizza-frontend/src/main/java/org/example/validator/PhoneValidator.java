package org.example.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator {

    private static final String PHONE_PATTERN = "^\\+\\d{2}\\s\\d{2}\\s\\d{3}-\\d{4}$";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || !(value instanceof String) || !((String) value).matches(PHONE_PATTERN)) {
            throw new ValidatorException(new FacesMessage("Invalid phone number format."));
        }
    }
}