package glescroel.escalade.mapper;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LongueurMapper {

    LongueurMapper INSTANCE = Mappers.getMapper(LongueurMapper.class);

    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    Longueur map(LongueurDto longueurDto);
    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    LongueurDto map(Longueur longueur);

    default List<Longueur> dtosToLongueurs(List<LongueurDto> longueurDtos) {
        List<Longueur> longueurs = new ArrayList<>();
        for (LongueurDto longueurDto : longueurDtos) {
            longueurs.add(this.map(longueurDto));
        }
        return longueurs;
    }

    default List<LongueurDto> longueursToDtos(List<Longueur> longueurs) {
        List<LongueurDto> longueurDtoList = new ArrayList<>();
        for (Longueur longueur : longueurs) {
            longueurDtoList.add(this.map(longueur));
        }
        return longueurDtoList;
    }

    //child
    @Mappings({
//            @Mapping(target = "longueurs", ignore = true)
            @Mapping(target = "secteur", ignore = true)
    })
    VoieDto map(Voie voie);

    @Mappings({
//            @Mapping(target = "longueurs", ignore = true)
            @Mapping(target = "secteur", ignore = true)
    })
    Voie map(VoieDto voieDto);
}
