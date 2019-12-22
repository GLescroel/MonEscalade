package glescroel.escalade.mapper;

import glescroel.escalade.dto.LongueurDto;
import glescroel.escalade.dto.SecteurDto;
import glescroel.escalade.dto.VoieDto;
import glescroel.escalade.model.Longueur;
import glescroel.escalade.model.Secteur;
import glescroel.escalade.model.Voie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface VoieMapper {

    VoieMapper INSTANCE = Mappers.getMapper(VoieMapper.class);

//    Voie map(VoieDto voieDto);
//    VoieDto map(Voie voie);

    default List<Voie> dtosToVoies(List<VoieDto> voieDtos) {
        List<Voie> voies = new ArrayList<>();
        for (VoieDto voieDto : voieDtos) {
            voies.add(this.map(voieDto));
        }
        return voies;
    }

    default List<VoieDto> voiesToDtos(List<Voie> voies) {
        List<VoieDto> voieDtoList = new ArrayList<>();
        for (Voie voie : voies) {
            voieDtoList.add(this.map(voie));
        }
        return voieDtoList;
    }

    //child
    @Mappings({
//            @Mapping(target = "voies", ignore = true)
    })
    SecteurDto map(Secteur secteur);

    @Mappings({
//            @Mapping(target = "voies", ignore = true)
    })
    Secteur map(SecteurDto secteurDto);

    //parent
    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    LongueurDto map(Longueur longueur);

    @Mappings({
            @Mapping(target = "voie.longueurs", ignore = true),
            @Mapping(target = "voie", ignore = true)
    })
    Longueur map(LongueurDto longueurDto);






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
