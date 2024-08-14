package com.farmacy.Modules.modeadministration.domain.service;

import java.util.Optional;

import com.farmacy.Modules.modeadministration.domain.entity.ModeAdministration;

public interface ModeAdministrationService {
    void createModeAdministration(ModeAdministration modeAdministration);
    Optional<ModeAdministration> readModeAdministration(int id);
    void updateModeAdministration(ModeAdministration modeAdministration, int id);
    void deleteModeAdministration(int id);
}