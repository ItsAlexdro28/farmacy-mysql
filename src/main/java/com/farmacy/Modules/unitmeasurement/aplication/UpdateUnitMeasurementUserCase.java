package com.farmacy.Modules.unitmeasurement.aplication;

import com.farmacy.Modules.unitmeasurement.domain.entity.UnitMeasurement;
import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;

public class UpdateUnitMeasurementUserCase {
    private final UnitMeasurementService unitMeasurementService;

    public UpdateUnitMeasurementUserCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(UnitMeasurement unitMeasurement, int idUm) {
        unitMeasurementService.updateUnitMeasurement(unitMeasurement, idUm);
    }
}