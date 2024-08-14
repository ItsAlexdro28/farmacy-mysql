package com.farmacy.Modules.activeprinciple.aplication;

import com.farmacy.Modules.activeprinciple.domain.service.ActivePrincipleService;

public class DeleteActivePrincipleUserCase {
    private final ActivePrincipleService activePrincipleService;

    public DeleteActivePrincipleUserCase(ActivePrincipleService activePrincipleService) {
        this.activePrincipleService = activePrincipleService;
    }

    public void execute(int idAp) {
        activePrincipleService.deleteActivePrinciple(idAp);
    }
}