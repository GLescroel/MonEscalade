package glescroel.escalade.dto;

import glescroel.escalade.model.Continent;
import glescroel.escalade.model.Pays;

public class PaysDto {

    private Integer id;
    private String nom;
    private Continent continent;

    public PaysDto(){};

    public PaysDto(Pays pays) {
        this.id = pays.getId();
        this.nom = pays.getNom();
        this.continent = pays.getContinent();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
