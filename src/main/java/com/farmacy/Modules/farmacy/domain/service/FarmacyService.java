package com.farmacy.Modules.farmacy.domain.service;

import java.util.Optional;

import com.farmacy.Modules.farmacy.domain.entity.Farmacy;

public interface FarmacyService {
    void createFarmacy(Farmacy farmacy);
    Optional<Farmacy> readFarmacy(int idFarmacy);
    void updateFarmacy(Farmacy farmacy, int idFarmacy);
    void deleteFarmacy(int idFarmacy);
}
