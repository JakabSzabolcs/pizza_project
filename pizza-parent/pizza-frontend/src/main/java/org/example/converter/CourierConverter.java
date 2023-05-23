package org.example.converter;


import org.example.entity.Courier;
import org.example.service.CourierService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("courierConverter")
public class CourierConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                Long id = Long.parseLong(value);
                CourierService courierService = context.getApplication().evaluateExpressionGet(context, "#{courierService}", CourierService.class);
                return courierService.findById(id);
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Invalid value"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Courier) {
            return String.valueOf(((Courier) value).getId());
        }
        return null;
    }
}