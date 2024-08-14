package com.farmacy.Modules.activeprinciple.domain.entity;

public class ActivePrinciple {
    private int idAp;
    private String nameAp;

    public ActivePrinciple() {
    }

    public ActivePrinciple(int idAp, String nameAp) {
        this.idAp = idAp;
        this.nameAp = nameAp;
    }

    public int getIdAp() {
        return idAp;
    }

    public void setIdAp(int idAp) {
        this.idAp = idAp;
    }

    public String getNameAp() {
        return nameAp;
    }

    public void setNameAp(String nameAp) {
        this.nameAp = nameAp;
    }
}
