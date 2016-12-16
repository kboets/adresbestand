package boets.adresbestand.web.validation;

import boets.adresbestand.web.form.SearchAddressForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Asus on 5/12/2016.
 */
public class SearchAddressFormValidation implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return (SearchAddressForm.class).isAssignableFrom(aClass);

    }

    @Override
    public void validate(Object object, Errors errors) {
        SearchAddressForm form = (SearchAddressForm) object;
        if(StringUtils.isBlank(form.getFirstName()) && StringUtils.isBlank(form.getLastName())) {
            errors.reject("error.no.searchCriteria");
        }

    }
}
