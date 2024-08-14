package com.farmacy.Modules.unitmeasurement.aplication;

import com.farmacy.Modules.unitmeasurement.domain.service.UnitMeasurementService;

public class DeleteUnitMeasurementUserCase {
    private final UnitMeasurementService unitMeasurementService;

    public DeleteUnitMeasurementUserCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(int idUm) {
        unitMeasurementService.deleteUnitMeasurement(idUm);
    }
}