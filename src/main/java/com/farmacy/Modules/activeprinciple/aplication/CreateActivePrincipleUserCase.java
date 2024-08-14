package com.farmacy.Modules.activeprinciple.aplication;

import com.farmacy.Modules.activeprinciple.domain.entity.ActivePrinciple;
import com.farmacy.Modules.activeprinciple.domain.service.ActivePrincipleService;

public class CreateActivePrincipleUserCase {
    private final ActivePrincipleService activePrincipleService;

    public CreateActivePrincipleUserCase(ActivePrincipleService activePrincipleService) {
        this.activePrincipleService = activePrincipleService;
    }

    public void execute(ActivePrinciple activePrinciple) {
        activePrincipleService.createActivePrinciple(activePrinciple);
    }
}
