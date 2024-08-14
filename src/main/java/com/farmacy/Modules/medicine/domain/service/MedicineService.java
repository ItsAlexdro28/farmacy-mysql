package com.farmacy.Modules.medicine.domain.service;

import java.util.Optional;

import com.farmacy.Modules.medicine.domain.entity.Medicine;

public interface MedicineService {
    void createMedicine(Medicine medicine);
    Optional<Medicine> readMedicine(int id);
    void updateMedicine(Medicine medicine, int id);
    void deleteMedicine(int id);
}