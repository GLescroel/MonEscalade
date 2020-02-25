package glescroel.escalade.mapper;

import glescroel.escalade.dto.TopoDto;
import glescroel.escalade.model.Topo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {UtilisateurMapper.class})
public interface TopoMapper {

    TopoMapper INSTANCE = Mappers.getMapper(TopoMapper.class);
    UtilisateurMapper UTILISATEUR_MAPPER = Mappers.getMapper(UtilisateurMapper.class);

    @Mappings({
            @Mapping(target = "proprietaire", ignore = true),
            @Mapping(target = "proprietaire.topos", ignore = true),
            @Mapping(target = "proprietaire.commentaires", ignore = true),
            @Mapping(target = "emprunteur", ignore = true),
            @Mapping(target = "emprunteur.topos", ignore = true),
            @Mapping(target = "emprunteur.commentaires", ignore = true)
    })
    Topo map(TopoDto topoDto);

    @AfterMapping
    static void addUtilisateurs(TopoDto topoDto, @MappingTarget Topo topo) {
        topo.setProprietaire(UTILISATEUR_MAPPER.map(topoDto.getProprietaire()));
        topo.setEmprunteur(UTILISATEUR_MAPPER.map(topoDto.getEmprunteur()));
    }

    @Mappings({
//            @Mapping(target = "proprietaire", ignore = true),
            @Mapping(target = "proprietaire.topos", ignore = true),
            @Mapping(target = "proprietaire.commentaires", ignore = true),
//            @Mapping(target = "emprunteur", ignore = true),
            @Mapping(target = "emprunteur.topos", ignore = true),
            @Mapping(target = "emprunteur.commentaires", ignore = true)
    })
    TopoDto map(Topo topo);

    @AfterMapping
    static void addUtilisateursToDto(Topo topo, @MappingTarget TopoDto topoDto) {
        topoDto.setProprietaire(UTILISATEUR_MAPPER.map(topo.getProprietaire()));
        topoDto.setEmprunteur(UTILISATEUR_MAPPER.map(topo.getEmprunteur()));
    }

    default List<Topo> dtosToTopos(List<TopoDto> topoDtoList) {
        if (topoDtoList == null) {
            return new ArrayList<>();
        }
        List<Topo> topos = new ArrayList<>();
        for (TopoDto topoDto : topoDtoList) {
            topos.add(this.map(topoDto));
        }
        return topos;
    }

    default List<TopoDto> toposToDtos(List<Topo> topos) {
        if (topos == null) {
            return new ArrayList<>();
        }
        List<TopoDto> topoDtoList = new ArrayList<>();
        for (Topo topo : topos) {
            topoDtoList.add(this.map(topo));
        }
        return topoDtoList;
    }
}
