package glescroel.escalade.dto;

public class LocalisationDto {

    private Integer id;
    private PaysDto pays;
    private ContinentDto continent;
    private String region;
    private String departement;
    private String ville;
    private String adresse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaysDto getPays() {
        return pays;
    }

    public void setPays(PaysDto pays) {
        this.pays = pays;
    }

    public ContinentDto getContinent() {
        return continent;
    }

    public void setContinent(ContinentDto continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
