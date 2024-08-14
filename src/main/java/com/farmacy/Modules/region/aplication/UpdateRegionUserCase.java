package com.farmacy.Modules.region.aplication;
import com.farmacy.Modules.region.domain.entity.Region;
import com.farmacy.Modules.region.domain.service.RegionService;

public class UpdateRegionUserCase {
    private RegionService regionService;

    public UpdateRegionUserCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region, String codeReg) {
        regionService.updateRegion(region, codeReg);
    }
}