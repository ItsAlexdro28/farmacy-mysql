package com.farmacy.Modules.laboratory.aplication;

import com.farmacy.Modules.laboratory.domain.entity.Laboratory;
import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;

public class UpdateLaboratoryUserCase {
    private final LaboratoryService laboratoryService;

    public UpdateLaboratoryUserCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(Laboratory laboratory, int id) {
        laboratoryService.updateLaboratory(laboratory, id);
    }
}
