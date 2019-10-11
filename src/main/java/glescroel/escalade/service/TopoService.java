package glescroel.escalade.service;

import glescroel.escalade.dto.TopoDto;
import glescroel.escalade.model.Topo;
import glescroel.escalade.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopoService {

    @Autowired
    private TopoRepository topoRepository;


    public TopoDto getTopoByNom(String nom){
        Optional<Topo> result = topoRepository.findByNomIgnoreCase(nom);
        TopoDto topoDto = null;
        if (result.isPresent()){
            topoDto = new TopoDto(result.get());
        }
        return topoDto;
    }

}
