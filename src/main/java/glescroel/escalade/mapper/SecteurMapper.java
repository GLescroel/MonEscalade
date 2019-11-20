package glescroel.escalade.mapper;

import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.model.Secteur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SecteurMapper {

    SecteurMapper INSTANCE = Mappers.getMapper(SecteurMapper.class);

    SecteurDto map(Secteur secteur);
    Secteur map(SecteurDto secteurDto);

    List<Secteur> dtosToSecteurs(List<SecteurDto> secteursDto);
    List<SecteurDto> secteursToDtos(List<Secteur> secteurs);
}
