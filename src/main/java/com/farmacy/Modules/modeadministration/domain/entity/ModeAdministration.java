package com.farmacy.Modules.modeadministration.domain.entity;
public class ModeAdministration {
    private int id;
    private String descriptionMode;

    public ModeAdministration() {
    }

    public ModeAdministration(int id, String descriptionMode) {
        this.id = id;
        this.descriptionMode = descriptionMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptionMode() {
        return descriptionMode;
    }

    public void setDescriptionMode(String descriptionMode) {
        this.descriptionMode = descriptionMode;
    }
}