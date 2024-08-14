package com.farmacy.Modules.region.aplication;
import java.util.Optional;

import com.farmacy.Modules.region.domain.entity.Region;
import com.farmacy.Modules.region.domain.service.RegionService;

public class ReadRegionUserCase {
    private RegionService regionService;

    public ReadRegionUserCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String codeReg) {
        return regionService.readRegion(codeReg);
    }
}