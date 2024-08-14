package com.farmacy.Modules.farmacymedicine.aplication;

import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;
import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;

public class UpdateFarmacyMedicineUserCase {
    private FarmacyMedicineService farmacyMedicineService;

    public UpdateFarmacyMedicineUserCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public void execute(FarmacyMedicine farmacyMedicine, int idFarmacy, int idMedicineFarm) {
        farmacyMedicineService.updateFarmacyMedicine(farmacyMedicine, idFarmacy, idMedicineFarm);
    }
}
