package com.farmacy.Modules.medicine.aplication;

import com.farmacy.Modules.medicine.domain.entity.Medicine;
import com.farmacy.Modules.medicine.domain.service.MedicineService;

public class CreateMedicineUserCase {
    private MedicineService medicineService;

    public CreateMedicineUserCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine) {
        medicineService.createMedicine(medicine);
    }
}
