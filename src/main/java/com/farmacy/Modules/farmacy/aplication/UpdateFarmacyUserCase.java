package com.farmacy.Modules.farmacy.aplication;

import com.farmacy.Modules.farmacy.domain.entity.Farmacy;
import com.farmacy.Modules.farmacy.domain.service.FarmacyService;

public class UpdateFarmacyUserCase {
    private FarmacyService farmacyService;

    public UpdateFarmacyUserCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public void execute(Farmacy farmacy, int idFarmacy) {
        farmacyService.updateFarmacy(farmacy, idFarmacy);
    }
}
