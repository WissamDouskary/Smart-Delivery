package com.smartlogi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinataire;
    private String adresse;
    private double poids;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    @JsonBackReference
    private Livreur livreur;

    public Colis(String destinataire, String adresse, double poids, String statut, Livreur livreur) {
        this.destinataire = destinataire;
        this.adresse = adresse;
        this.poids = poids;
        this.statut = statut;
        this.livreur = livreur;
    }

    public Colis() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Long getId() {
        return id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getPoids() {
        return poids;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }
}
