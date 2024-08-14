package com.farmacy.Modules.unitmeasurement.aplication;

import java.util.Optional;

import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;
import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;

public class ReadUnitMeasurementUserCase {
    private final UnitMeasurementService unitMeasurementService;

    public ReadUnitMeasurementUserCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public Optional<UnitMeasurement> execute(int idUm) {
        return unitMeasurementService.readUnitMeasurement(idUm);
    }
}