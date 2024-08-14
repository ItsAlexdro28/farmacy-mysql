package com.farmacy.Modules.modeadministration.aplication;
import java.util.Optional;

import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;

public class ReadModeAdministrationUserCase {
    private final ModeAdministrationService modeAdministrationService;

    public ReadModeAdministrationUserCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public Optional<ModeAdministration> execute(int id) {
        return modeAdministrationService.readModeAdministration(id);
    }
}