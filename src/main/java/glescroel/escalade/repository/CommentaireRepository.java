package glescroel.escalade.repository;

import glescroel.escalade.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

    Commentaire save(Commentaire commentaire);
}
