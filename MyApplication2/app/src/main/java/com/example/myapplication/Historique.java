package com.example.myapplication;
import android.graphics.Bitmap;

public class Historique{
    int id_historique ;
    String nom_maladie;
    int pourcentage ;
    int confirmation;
    Bitmap photo;
    public Historique(String nom_maladie, int pourcentage, int confirmation,Bitmap photo
    ) {
        this.nom_maladie = nom_maladie;
        this.pourcentage = pourcentage;
        this.confirmation = confirmation;
        this.photo = photo;
    }

    public Historique() {
    }

    public Historique(int id_historique, String nom_maladie, int pourcentage, int confirmation,Bitmap photo) {
        this.id_historique = id_historique;
        this.nom_maladie = nom_maladie;
        this.pourcentage = pourcentage;
        this.confirmation = confirmation;
        this.photo = photo;
    }

    public int getId_historique() {
        return id_historique;
    }

    public void setId_historique(int id_historique) {
        this.id_historique = id_historique;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getNom_maladie() {
        return nom_maladie;
    }

    public void setNom_maladie(String nom_maladie) {
        this.nom_maladie = nom_maladie;
    }
}