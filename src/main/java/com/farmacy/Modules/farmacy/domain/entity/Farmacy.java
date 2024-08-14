package com.farmacy.Modules.farmacy.domain.entity;

public class Farmacy {
    private int idFarmacy;
    private String nameFarmacy;
    private String addressFarmacy;
    private float longitude;
    private float latitude;
    private String codeCityFarm;
    private String logoFarmacy;

    public Farmacy() {
    }

    public Farmacy(int idFarmacy, String nameFarmacy, String addressFarmacy, float longitude, float latitude, String codeCityFarm, String logoFarmacy) {
        this.idFarmacy = idFarmacy;
        this.nameFarmacy = nameFarmacy;
        this.addressFarmacy = addressFarmacy;
        this.longitude = longitude;
        this.latitude = latitude;
        this.codeCityFarm = codeCityFarm;
        this.logoFarmacy = logoFarmacy;
    }

    public int getIdFarmacy() {
        return idFarmacy;
    }

    public void setIdFarmacy(int idFarmacy) {
        this.idFarmacy = idFarmacy;
    }

    public String getNameFarmacy() {
        return nameFarmacy;
    }

    public void setNameFarmacy(String nameFarmacy) {
        this.nameFarmacy = nameFarmacy;
    }

    public String getAddressFarmacy() {
        return addressFarmacy;
    }

    public void setAddressFarmacy(String addressFarmacy) {
        this.addressFarmacy = addressFarmacy;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getCodeCityFarm() {
        return codeCityFarm;
    }

    public void setCodeCityFarm(String codeCityFarm) {
        this.codeCityFarm = codeCityFarm;
    }

    public String getLogoFarmacy() {
        return logoFarmacy;
    }

    public void setLogoFarmacy(String logoFarmacy) {
        this.logoFarmacy = logoFarmacy;
    }

    
    
}