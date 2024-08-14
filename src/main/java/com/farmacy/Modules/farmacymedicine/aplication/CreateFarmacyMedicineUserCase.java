package com.farmacy.Modules.farmacymedicine.aplication;

import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;
import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;

public class CreateFarmacyMedicineUserCase {
    private FarmacyMedicineService farmacyMedicineService;

    public CreateFarmacyMedicineUserCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public void execute(FarmacyMedicine farmacyMedicine) {
        farmacyMedicineService.createFarmacyMedicine(farmacyMedicine);
    }
}
