package com.farmacy.Modules.country.aplication;

import com.farmacy.Modules.country.domain.entity.Country;
import com.farmacy.Modules.country.domain.service.CountryService;

public class CreateCountryUserCase {
    public final CountryService countryService;

    public CreateCountryUserCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute (Country country) {
        countryService.createCountry(country);
    }
}