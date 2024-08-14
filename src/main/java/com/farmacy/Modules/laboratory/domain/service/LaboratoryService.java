package com.farmacy.Modules.laboratory.domain.service;

import java.util.Optional;

import com.farmacy.Modules.laboratory.domain.entity.Laboratory;

public interface LaboratoryService {
    void createLaboratory(Laboratory laboratory);
    Optional<Laboratory> readLaboratory(int id);
    void updateLaboratory(Laboratory laboratory, int id);
    void deleteLaboratory(int id);
}
