package com.farmacy.Modules.activeprinciple.domain.service;

import java.util.Optional;

import com.farmacy.Modules.activeprinciple.domain.entity.ActivePrinciple;

public interface ActivePrincipleService {
    void createActivePrinciple(ActivePrinciple activePrinciple);
    Optional<ActivePrinciple> readActivePrinciple(int idAp);
    void updateActivePrinciple(ActivePrinciple activePrinciple, int idAp);
    void deleteActivePrinciple(int idAp);
}