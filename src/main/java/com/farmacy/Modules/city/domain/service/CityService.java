package com.farmacy.Modules.city.domain.service;
import java.util.Optional;

import com.farmacy.Modules.city.domain.entity.City;

public interface CityService {
    void createCity(City city);
    Optional<City> readCity(String codeCity);
    void updateCity(City city, String codeCity);
    void deleteCity(String codeCity);
}