package com.farmacy.Modules.city.aplication;

import com.farmacy.Modules.city.domain.entity.City;
import com.farmacy.Modules.city.domain.service.CityService;

public class UpdateCityUserCase {
    private CityService cityService;

    public UpdateCityUserCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city, String codeCity) {
        cityService.updateCity(city, codeCity);
    }
}