package com.farmacy.Modules.activeprinciple.aplication;

import java.util.Optional;

import com.farmacy.Modules.activeprinciple.domain.entity.ActivePrinciple;
import com.farmacy.Modules.activeprinciple.domain.service.ActivePrincipleService;

public class ReadActivePrincipleUserCase {
    private final ActivePrincipleService activePrincipleService;

    public ReadActivePrincipleUserCase(ActivePrincipleService activePrincipleService) {
        this.activePrincipleService = activePrincipleService;
    }

    public Optional<ActivePrinciple> execute(int idAp) {
        return activePrincipleService.readActivePrinciple(idAp);
    }
}