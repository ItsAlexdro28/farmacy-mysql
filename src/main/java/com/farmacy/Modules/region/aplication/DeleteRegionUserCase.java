package com.farmacy.Modules.region.aplication;

import com.farmacy.Modules.region.domain.service.RegionService;

public class DeleteRegionUserCase {
    private RegionService regionService;

    public DeleteRegionUserCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(String codeReg) {
        regionService.deleteRegion(codeReg);
    }
}