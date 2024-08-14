package com.farmacy.Modules.region.domain.service;

import java.util.Optional;

import com.farmacy.Modules.region.domain.entity.Region;

public interface RegionService {
    void createRegion(Region region);
    Optional<Region> readRegion(String codeReg);
    void updateRegion(Region region, String codeReg);
    void deleteRegion(String codeReg);
}
