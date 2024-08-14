package com.farmacy.Modules.laboratory.aplication;

import com.farmacy.Modules.laboratory.domain.entity.Laboratory;
import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;

public class CreateLaboratoryUserCase {
    private final LaboratoryService laboratoryService;

    public CreateLaboratoryUserCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(Laboratory laboratory) {
        laboratoryService.createLaboratory(laboratory);
    }
}