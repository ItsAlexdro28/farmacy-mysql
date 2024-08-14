package com.farmacy.Modules.medicine.aplication;

import java.util.Optional;

import com.farmacy.Modules.medicine.domain.entity.Medicine;
import com.farmacy.Modules.medicine.domain.service.MedicineService;

public class ReadMedicineUserCase {
    private MedicineService medicineService;

    public ReadMedicineUserCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public Optional<Medicine> execute(int id) {
        return medicineService.readMedicine(id);
    }
}
