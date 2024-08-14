package com.farmacy.Modules.farmacymedicine.domain.entity;

public class FarmacyMedicine {
    private int idFarmacy;
    private int idMedicineFarm;
    private float price;

    public FarmacyMedicine() {
    }

    public FarmacyMedicine(int idFarmacy, int idMedicineFarm, float price) {
        this.idFarmacy = idFarmacy;
        this.idMedicineFarm = idMedicineFarm;
        this.price = price;
    }

    public int getIdFarmacy() {
        return idFarmacy;
    }

    public void setIdFarmacy(int idFarmacy) {
        this.idFarmacy = idFarmacy;
    }

    public int getIdMedicineFarm() {
        return idMedicineFarm;
    }

    public void setIdMedicineFarm(int idMedicineFarm) {
        this.idMedicineFarm = idMedicineFarm;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}