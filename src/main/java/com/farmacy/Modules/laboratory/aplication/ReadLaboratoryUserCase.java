package com.farmacy.Modules.laboratory.aplication;

import java.util.Optional;

import com.farmacy.Modules.laboratory.domain.entity.Laboratory;
import com.farmacy.Modules.laboratory.domain.service.LaboratoryService;

public class ReadLaboratoryUserCase {
    private final LaboratoryService laboratoryService;

    public ReadLaboratoryUserCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public Optional<Laboratory> execute(int id) {
        return laboratoryService.readLaboratory(id);
    }
}