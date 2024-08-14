package com.farmacy.Modules.city.aplication;
import java.util.Optional;

import com.farmacy.Modules.city.domain.entity.City;
import com.farmacy.Modules.city.domain.service.CityService;

public class ReadCityUserCase {
    private CityService cityService;

    public ReadCityUserCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String codeCity) {
        return cityService.readCity(codeCity);
    }
}