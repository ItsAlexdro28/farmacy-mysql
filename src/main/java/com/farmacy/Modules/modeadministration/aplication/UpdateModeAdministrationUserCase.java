package com.farmacy.Modules.modeadministration.aplication;
import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;

public class UpdateModeAdministrationUserCase {
    private final ModeAdministrationService modeAdministrationService;

    public UpdateModeAdministrationUserCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(ModeAdministration modeAdministration, int id) {
        modeAdministrationService.updateModeAdministration(modeAdministration, id);
    }
}