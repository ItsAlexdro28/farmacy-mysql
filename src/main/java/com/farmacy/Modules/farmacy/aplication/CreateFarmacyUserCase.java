package com.farmacy.Modules.farmacy.aplication;

import com.farmacy.Modules.farmacy.domain.entity.Farmacy;
import com.farmacy.Modules.farmacy.domain.service.FarmacyService;

public class CreateFarmacyUserCase {
    private FarmacyService farmacyService;

    public CreateFarmacyUserCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public void execute(Farmacy farmacy) {
        farmacyService.createFarmacy(farmacy);
    }
}
