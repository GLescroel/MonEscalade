package glescroel.escalade.repository;

import glescroel.escalade.model.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {
    Optional<Topo> findByNomIgnoreCase(String nom);
    List<Topo> findAllByProprietaireId(int userId);
}
