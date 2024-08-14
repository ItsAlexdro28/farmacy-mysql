package com.farmacy.Modules.laboratory.domain.entity;

public class Laboratory {
    private int id;
    private String nameLab;
    private String codeCityReg;

    public Laboratory() {
    }

    public Laboratory(int id, String nameLab, String codeCityReg) {
        this.id = id;
        this.nameLab = nameLab;
        this.codeCityReg = codeCityReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLab() {
        return nameLab;
    }

    public void setNameLab(String nameLab) {
        this.nameLab = nameLab;
    }

    public String getCodeCityReg() {
        return codeCityReg;
    }

    public void setCodeCityReg(String codeCityReg) {
        this.codeCityReg = codeCityReg;
    }
}