package com.farmacy.Modules.country.aplication;

import com.farmacy.Modules.country.domain.entity.Country;
import com.farmacy.Modules.country.domain.service.CountryService;

public class UpdateCountryUserCase {
    public final CountryService countryService;

    public UpdateCountryUserCase(CountryService countryService) {
        this.countryService = countryService;
    }
    
    public void execute(Country country, String code) {
        countryService.updateCountry(country, code);
    }
}