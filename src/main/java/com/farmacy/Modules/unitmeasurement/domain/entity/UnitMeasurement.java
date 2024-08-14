package com.farmacy.Modules.unitmeasurement.domain.entity;

public class UnitMeasurement {
    private int idUm;
    private String nameUm;

    public UnitMeasurement() {
    }

    public UnitMeasurement(int idUm, String nameUm) {
        this.idUm = idUm;
        this.nameUm = nameUm;
    }

    public int getIdUm() {
        return idUm;
    }

    public void setIdUm(int idUm) {
        this.idUm = idUm;
    }

    public String getNameUm() {
        return nameUm;
    }

    public void setNameUm(String nameUm) {
        this.nameUm = nameUm;
    }
}