package glescroel.escalade.dto;

public class CommentaireDto {

    private Integer id;
    private String commentaire;
    private TopoDto topo;
    private SiteDto site;
    private SecteurDto secteur;
    private VoieDto voie;
    private LongueurDto longueur;
    private UtilisateurDto utilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public TopoDto getTopo() {
        return topo;
    }

    public void setTopo(TopoDto topo) {
        this.topo = topo;
    }

    public SiteDto getSite() {
        return site;
    }

    public void setSite(SiteDto site) {
        this.site = site;
    }

    public SecteurDto getSecteur() {
        return secteur;
    }

    public void setSecteur(SecteurDto secteur) {
        this.secteur = secteur;
    }

    public VoieDto getVoie() {
        return voie;
    }

    public void setVoie(VoieDto voie) {
        this.voie = voie;
    }

    public LongueurDto getLongueur() {
        return longueur;
    }

    public void setLongueur(LongueurDto longueur) {
        this.longueur = longueur;
    }

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }
}
