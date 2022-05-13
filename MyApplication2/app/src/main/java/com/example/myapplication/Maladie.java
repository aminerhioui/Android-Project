package com.example.myapplication;

import android.graphics.Bitmap;

public class Maladie {
    int id_maladie ;
    String nom_maladie;
    String description ;
    String causes ;
    String suggestion;
    Bitmap image;

    public Maladie() {
    }

    public Maladie(int id_maladie, String nom_maladie, String description, String causes, String suggestion, Bitmap image) {
        this.id_maladie = id_maladie;
        this.nom_maladie = nom_maladie;
        this.description = description;
        this.causes = causes;
        this.suggestion = suggestion;
        this.image = image;
    }

    public Maladie(String nom_maladie, String description, String causes, String suggestion, Bitmap image) {
        this.nom_maladie = nom_maladie;
        this.description = description;
        this.causes = causes;
        this.suggestion = suggestion;
        this.image = image;
    }

    public int getId_maladie() {
        return id_maladie;
    }

    public void setId_maladie(int id_maladie) {
        this.id_maladie = id_maladie;
    }

    public String getNom_maladie() {
        return nom_maladie;
    }

    public void setNom_maladie(String nom_maladie) {
        this.nom_maladie = nom_maladie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
