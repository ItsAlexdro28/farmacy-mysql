package com.farmacy.Modules.country.aplication;

import com.farmacy.Modules.country.domain.service.CountryService;

public class DeleteCountryUserCase {
    public final CountryService countryService;

    public DeleteCountryUserCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String codeCountry) {
        countryService.deleteCountry(codeCountry);
    }
    
}