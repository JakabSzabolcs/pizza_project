package org.example.converter;
import org.example.entity.Pizza;
import org.example.service.PizzaService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("pizzaConverter")
public class PizzaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                Long id = Long.parseLong(value);
                PizzaService pizzaService = context.getApplication().evaluateExpressionGet(context, "#{pizzaService}", PizzaService.class);
                return pizzaService.findById(id);
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Invalid value"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Pizza) {
            return String.valueOf(((Pizza) value).getId());
        }
        return null;
    }
}