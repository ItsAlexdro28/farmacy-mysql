package com.farmacy.Modules.country.domain.service;

import java.util.Optional;

import com.farmacy.Modules.country.domain.entity.Country;

public interface CountryService {
    public void createCountry(Country country);
    public Optional<Country> readCountry(String codeCountry);
    public void updateCountry(Country country, String code);
    public void deleteCountry(String codeCountry);
}