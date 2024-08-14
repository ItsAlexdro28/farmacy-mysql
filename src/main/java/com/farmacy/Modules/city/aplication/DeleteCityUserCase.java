package com.farmacy.Modules.city.aplication;
import com.farmacy.Modules.city.domain.service.CityService;

public class DeleteCityUserCase {
    private CityService cityService;

    public DeleteCityUserCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(String codeCity) {
        cityService.deleteCity(codeCity);
    }
}