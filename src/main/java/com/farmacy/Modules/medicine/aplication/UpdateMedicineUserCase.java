package com.farmacy.Modules.medicine.aplication;

import com.farmacy.Modules.medicine.domain.entity.Medicine;
import com.farmacy.Modules.medicine.domain.service.MedicineService;

public class UpdateMedicineUserCase {
    private MedicineService medicineService;

    public UpdateMedicineUserCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine, int id) {
        medicineService.updateMedicine(medicine, id);
    }
}
