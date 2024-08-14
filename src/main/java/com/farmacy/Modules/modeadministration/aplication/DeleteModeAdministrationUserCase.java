package com.farmacy.Modules.modeadministration.aplication;
import com.farmacy.Modules.modeadministration.domain.service.ModeAdministrationService;

public class DeleteModeAdministrationUserCase {
    private final ModeAdministrationService modeAdministrationService;

    public DeleteModeAdministrationUserCase(ModeAdministrationService modeAdministrationService) {
        this.modeAdministrationService = modeAdministrationService;
    }

    public void execute(int id) {
        modeAdministrationService.deleteModeAdministration(id);
    }
}