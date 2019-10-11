package glescroel.escalade.dto;

import glescroel.escalade.model.Continent;

public class ContinentDto {
    private Integer id;
    private String nom;

    public ContinentDto(Continent continent) {
        this.id = continent.getId();
        this.nom = continent.getNom();
    }

    public ContinentDto() {};

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
}
