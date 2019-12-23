package glescroel.escalade.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @ColumnTransformer(write = "UPPER(?)")
    @Size(message = ErrorMessages.PAYS_NOM_LENGTH, max = 50)
    private String nom;

    @ManyToOne
    private Continent continent;
}
