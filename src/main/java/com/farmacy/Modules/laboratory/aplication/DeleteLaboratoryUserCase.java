package com.farmacy.Modules.laboratory.aplication;

import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;

public class DeleteLaboratoryUserCase {
    private final LaboratoryService laboratoryService;

    public DeleteLaboratoryUserCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(int id) {
        laboratoryService.deleteLaboratory(id);
    }
}