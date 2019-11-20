package glescroel.escalade.mapper;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.model.Longueur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LongueurMapper {

    LongueurMapper INSTANCE = Mappers.getMapper(LongueurMapper.class);

    Longueur map(LongueurDto longueurDto);
    LongueurDto map(Longueur longueur);

    List<Longueur> dtosToLongueurs(List<LongueurDto> longueurDtos);
    List<LongueurDto> longueursToDtos(List<Longueur> longueurs);
}
