package hei.enjoyvoyage.entities;


public class User {

    private Integer id_utilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private Boolean admin;

    public User(Integer id_utilisateur, String nom, String prenom, String email, String mdp, boolean admin) {
        super();
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.admin = admin;
    }

    public User(Integer id_utilisateur, String nom, String prenom, String email) {
        super();
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public User(int id_utilisateur, String nom, String prenom, String email, boolean administrateur) {
        super();
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.admin = administrateur;
    }


    public Integer getId() {
        return id_utilisateur;
    }

    public void setId(Integer id_utilisateur) {
        this.id_utilisateur= id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() { return email; }

    public void setMail(String mail) {
        this.email = mail;
    }

    public String getMdp() { return mdp; }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Boolean getAdmin() { return admin; }

    public void setAdmin(boolean admin) { this.admin = admin;
    }

}
