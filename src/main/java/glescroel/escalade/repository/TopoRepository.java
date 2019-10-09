package glescroel.escalade.repository;

import glescroel.escalade.model.Topo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopoRepository extends JpaRepository<Topo, Integer> {
    Optional<Topo> findByNomIgnoreCase(String nom);
}
