package glescroel.escalade.service;

import glescroel.escalade.dto.TopoDto;
import glescroel.escalade.mapper.TopoMapper;
import glescroel.escalade.model.Topo;
import glescroel.escalade.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopoService {

    private static final TopoMapper TOPO_MAPPER = TopoMapper.INSTANCE;
    @Autowired
    private TopoRepository topoRepository;


    public TopoDto getTopoByNom(String nom){
        Optional<Topo> result = topoRepository.findByNomIgnoreCase(nom);
        TopoDto topoDto = null;
        if (result.isPresent()){
            topoDto = TOPO_MAPPER.map(result.get());
        }
        return topoDto;
    }

    public List<TopoDto> getAll() {
        return TOPO_MAPPER.toposToDtos(topoRepository.findAll());
    }

    public List<TopoDto> getToposByUtilisateur(int userId) {
        return TOPO_MAPPER.toposToDtos(topoRepository.findAllByProprietaireId(userId));
    }

    public void save(TopoDto topo) {
        topoRepository.save(TOPO_MAPPER.map(topo));
    }
}
