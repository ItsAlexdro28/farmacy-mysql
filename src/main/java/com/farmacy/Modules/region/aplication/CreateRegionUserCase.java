package com.farmacy.Modules.region.aplication;
import com.farmacy.Modules.region.domain.entity.Region;
import com.farmacy.Modules.region.domain.service.RegionService;

public class CreateRegionUserCase {
    private RegionService regionService;

    public CreateRegionUserCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region) {
        regionService.createRegion(region);
    }
}