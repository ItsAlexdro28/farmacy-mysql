package com.farmacy.Modules.unitmeasurement.domain.service;
import java.util.Optional;

import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;

public interface UnitMeasurementService {
    void createUnitMeasurement(UnitMeasurement unitMeasurement);
    Optional<UnitMeasurement> readUnitMeasurement(int idUm);
    void updateUnitMeasurement(UnitMeasurement unitMeasurement, int idUm);
    void deleteUnitMeasurement(int idUm);
}