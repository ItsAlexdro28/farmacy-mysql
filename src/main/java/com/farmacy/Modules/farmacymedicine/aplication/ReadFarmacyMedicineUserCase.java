package com.farmacy.Modules.farmacymedicine.aplication;

import java.util.Optional;

import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;
import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;

public class ReadFarmacyMedicineUserCase {
    private FarmacyMedicineService farmacyMedicineService;

    public ReadFarmacyMedicineUserCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public Optional<FarmacyMedicine> execute(int idFarmacy, int idMedicineFarm) {
        return farmacyMedicineService.readFarmacyMedicine(idFarmacy, idMedicineFarm);
    }
}