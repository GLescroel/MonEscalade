package glescroel.escalade.service;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.model.Continent;
import glescroel.escalade.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    public ContinentDto getContinentByNom(String nom){
        Optional<Continent> result = continentRepository.findByNomIgnoreCase(nom);
        ContinentDto continentDto = null;
        if (result.isPresent()){
            continentDto = new ContinentDto(result.get());
        }
        return continentDto;
    }

    public List<Continent> getAll() {
        return continentRepository.findAll();
    }

    public ContinentDto getContinentById(Integer id) {
        Optional<Continent> result = continentRepository.findById(id);
        ContinentDto continentDto = null;
        if (result.isPresent()){
            continentDto = new ContinentDto(result.get());
        }
        return continentDto;
    }
}
