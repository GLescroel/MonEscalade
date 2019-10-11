package glescroel.escalade.service;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.model.Continent;
import glescroel.escalade.model.Pays;
import glescroel.escalade.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public PaysDto getPaysByNom(String nom) {
        Optional<Pays> result = paysRepository.findByNomIgnoreCase(nom);
        PaysDto paysDto = null;
        if (result.isPresent()) {
            paysDto = new PaysDto(result.get());
        }
        return paysDto;
    }

    public PaysDto getPaysById(Integer paysId) {
        Optional<Pays> result = paysRepository.findById(paysId);
        PaysDto paysDto = null;
        if (result.isPresent()) {
            paysDto = new PaysDto(result.get());
        }
        return paysDto;
    }

    public List<PaysDto> getPaysByContinent(ContinentDto continentDto) {
        Continent continent = new Continent();
        continent.setId(continentDto.getId());
        continent.setNom(continentDto.getNom());
        return convertPaysListToDtoList(paysRepository.findAllByContinentOrderByNomAsc(continent));
    }

    private List<PaysDto> convertPaysListToDtoList(List<Pays> paysList) {
        List<PaysDto> paysDtos = new ArrayList();
        for (Pays pays : paysList){
            paysDtos.add(new PaysDto(pays));
        }
        return paysDtos;

    }
}
