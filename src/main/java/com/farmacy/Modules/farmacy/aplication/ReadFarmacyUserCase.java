package com.farmacy.Modules.farmacy.aplication;

import java.util.Optional;

import com.farmacy.Modules.farmacy.domain.entity.Farmacy;
import com.farmacy.Modules.farmacy.domain.service.FarmacyService;

public class ReadFarmacyUserCase {
    private FarmacyService farmacyService;

    public ReadFarmacyUserCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public Optional<Farmacy> execute(int idFarmacy) {
        return farmacyService.readFarmacy(idFarmacy);
    }
}
