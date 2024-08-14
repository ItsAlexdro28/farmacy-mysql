package com.farmacy.Modules.medicine.aplication;

import com.farmacy.Modules.medicine.domain.service.MedicineService;

public class DeleteMedicineUserCase {
    private MedicineService medicineService;

    public DeleteMedicineUserCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(int id) {
        medicineService.deleteMedicine(id);
    }
}