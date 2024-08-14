package com.farmacy.Modules.region.domain.entity;

public class Region {
    private String codeReg;
    private String nameReg;
    private String codeCountry;

    public Region() {
    }

    public Region(String codeReg, String nameReg, String codeCountry) {
        this.codeReg = codeReg;
        this.nameReg = nameReg;
        this.codeCountry = codeCountry;
    }

    public String getCodeReg() {
        return codeReg;
    }

    public void setCodeReg(String codeReg) {
        this.codeReg = codeReg;
    }

    public String getNameReg() {
        return nameReg;
    }

    public void setNameReg(String nameReg) {
        this.nameReg = nameReg;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }
}
