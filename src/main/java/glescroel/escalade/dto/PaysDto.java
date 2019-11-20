package glescroel.escalade.dto;

public class PaysDto {

    private Integer id;
    private String nom;
    private ContinentDto continent;

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

    public ContinentDto getContinent() {
        return continent;
    }

    public void setContinent(ContinentDto continent) {
        this.continent = continent;
    }
}
