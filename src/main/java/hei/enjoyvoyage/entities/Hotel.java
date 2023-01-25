package hei.enjoyvoyage.entities;

import java.time.LocalDate;

public class Hotel {

    private Integer id_hotel;
    private String nom;
    private String ville;
    private String pays;
    private String description;
    private Double prix;
    private String photo;

    public Hotel(Integer id_hotel, String nom, String ville, String pays, String description, Double prix, String photo) {
        super();
        this.id_hotel = id_hotel;
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
        this.description = description;
        this.prix = prix;
        this.photo = photo;
    }

    public Hotel(int id_hotel, String nom,String ville,String pays,String photo) {
        super();
        this.id_hotel = id_hotel;
        this.pays=pays;
        this.ville=ville;
        this.nom = nom;
        this.photo = photo;
    }

    public Hotel(int id_hotel, String nom, String ville, String pays, Double prix, String photo) {
        super();
        this.id_hotel = id_hotel;
        this.pays=pays;
        this.ville=ville;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
    }



    public Integer getId() {
        return id_hotel;
    }

    public void setId(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() { return pays; }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() { return prix; }

    public void setPrix(Double prix) {
        this.prix = 0.00;
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) {
        this.photo = null;
    }



}
