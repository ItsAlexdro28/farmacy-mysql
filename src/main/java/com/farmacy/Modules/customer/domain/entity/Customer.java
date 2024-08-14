package com.farmacy.Modules.customer.domain.entity;

public class Customer {
    private String idCustomer;
    private String nameCustomer;
    private String lastNameCustomer;
    private String codeCityCustomer;
    private String emailCustomer;
    private String birthDate;
    private float longitude;
    private float latitude;

    public Customer() {
    }

    public Customer(String idCustomer, String nameCustomer, String lastNameCustomer, String codeCityCustomer, String emailCustomer, String birthDate, float longitude, float latitude) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.lastNameCustomer = lastNameCustomer;
        this.codeCityCustomer = codeCityCustomer;
        this.emailCustomer = emailCustomer;
        this.birthDate = birthDate;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getLastNameCustomer() {
        return lastNameCustomer;
    }

    public void setLastNameCustomer(String lastNameCustomer) {
        this.lastNameCustomer = lastNameCustomer;
    }

    public String getCodeCityCustomer() {
        return codeCityCustomer;
    }

    public void setCodeCityCustomer(String codeCityCustomer) {
        this.codeCityCustomer = codeCityCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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


}
