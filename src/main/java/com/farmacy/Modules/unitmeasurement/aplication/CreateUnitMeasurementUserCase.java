package com.farmacy.Modules.unitmeasurement.aplication;

import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;
import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;

public class CreateUnitMeasurementUserCase {
    private final UnitMeasurementService unitMeasurementService;

    public CreateUnitMeasurementUserCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(UnitMeasurement unitMeasurement) {
        unitMeasurementService.createUnitMeasurement(unitMeasurement);
    }
}