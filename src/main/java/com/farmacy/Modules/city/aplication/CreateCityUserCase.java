package com.farmacy.Modules.city.aplication;

import com.farmacy.Modules.city.domain.entity.City;
import com.farmacy.Modules.city.domain.service.CityService;

public class CreateCityUserCase {
    private CityService cityService;

    public CreateCityUserCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city) {
        cityService.createCity(city);
    }
}