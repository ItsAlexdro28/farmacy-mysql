package com.farmacy.Modules.country.aplication;

import java.util.Optional;

import com.farmacy.Modules.country.domain.entity.Country;
import com.farmacy.Modules.country.domain.service.CountryService;

public class ReadCountryUserCase {
    public final CountryService countryService;

    public ReadCountryUserCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> execute (String codeCoutry) {
        return countryService.readCountry(codeCoutry);
    }
}