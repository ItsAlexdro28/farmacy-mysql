package com.farmacy.Modules.farmacymedicine.aplication;

import com.farmacy.Modules.farmacymedicine.domain.service.FarmacyMedicineService;

public class DeleteFarmacyMedicineUserCase {
    private FarmacyMedicineService farmacyMedicineService;

    public DeleteFarmacyMedicineUserCase(FarmacyMedicineService farmacyMedicineService) {
        this.farmacyMedicineService = farmacyMedicineService;
    }

    public void execute(int idFarmacy, int idMedicineFarm) {
        farmacyMedicineService.deleteFarmacyMedicine(idFarmacy, idMedicineFarm);
    }
}