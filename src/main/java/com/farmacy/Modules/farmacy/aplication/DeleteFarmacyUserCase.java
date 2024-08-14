package com.farmacy.Modules.farmacy.aplication;

import com.farmacy.Modules.farmacy.domain.service.FarmacyService;

public class DeleteFarmacyUserCase {
    private FarmacyService farmacyService;

    public DeleteFarmacyUserCase(FarmacyService farmacyService) {
        this.farmacyService = farmacyService;
    }

    public void execute(int idFarmacy) {
        farmacyService.deleteFarmacy(idFarmacy);
    }
}

