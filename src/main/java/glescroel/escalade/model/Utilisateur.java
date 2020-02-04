package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable, UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utilisateur.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_NOM_LENGTH, min = 1, max = 30)
    private String nom;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_PRENOM_LENGTH, min = 1, max = 30)
    private String prenom;

    @Basic
    @Email
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.UTILISATEUR_EMAIL_LENGTH, min = 5, max = 50)
    private String email;

    @Basic
    @Size(message = ErrorMessages.UTILISATEUR_MDP_LENGTH, min = 8, max = 80)
    private String password;

    @ManyToOne
    private Role role;

    @OneToMany
    private List<Topo> topos;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "utilisateur", orphanRemoval = true)
    private List<Commentaire> commentaires;


    @Override
    public String getUsername() {
        return nom;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LOGGER.info("Authorities = " + role.getRole());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRole()));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
