package com.farmacy.Modules.modeadministration.aplication;
import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;

public class CreateModeAdministrationUserCase {
    private final ModeAdministrationService modeAdministrationService;

    public CreateModeAdministrationUserCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(ModeAdministration modeAdministration) {
        modeAdministrationService.createModeAdministration(modeAdministration);
    }
}