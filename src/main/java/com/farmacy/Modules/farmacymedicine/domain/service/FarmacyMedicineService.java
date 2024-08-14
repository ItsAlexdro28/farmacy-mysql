package com.farmacy.Modules.farmacymedicine.domain.service;

import java.util.Optional;

import com.farmacy.Modules.farmacymedicine.domain.entity.FarmacyMedicine;

public interface FarmacyMedicineService {
    void createFarmacyMedicine(FarmacyMedicine farmacyMedicine);
    Optional<FarmacyMedicine> readFarmacyMedicine(int idFarmacy, int idMedicineFarm);
    void updateFarmacyMedicine(FarmacyMedicine farmacyMedicine, int idFarmacy, int idMedicineFarm);
    void deleteFarmacyMedicine(int idFarmacy, int idMedicineFarm);
}
