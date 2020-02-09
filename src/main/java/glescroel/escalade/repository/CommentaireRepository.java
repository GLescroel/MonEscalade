package glescroel.escalade.repository;

import glescroel.escalade.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

    Commentaire save(Commentaire commentaire);
    List<Commentaire> findCommentairesByUtilisateur_IdOrderById(int idUtilisateur);
}
