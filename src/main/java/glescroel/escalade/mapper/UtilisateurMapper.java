package glescroel.escalade.mapper;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {CommentaireMapper.class, TopoMapper.class})
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    Utilisateur map(UtilisateurDto utilisateurDto);
    UtilisateurDto map(Utilisateur utilisateur);

    default List<Utilisateur> dtosToUtilisateurs(List<UtilisateurDto> utilisateurDtos) {
        if(utilisateurDtos == null) {
            return new ArrayList<>();
        }
        List<Utilisateur> utilisateurs = new ArrayList<>();
        for (UtilisateurDto utilisateurDto : utilisateurDtos) {
            utilisateurs.add(this.map(utilisateurDto));
        }
        return utilisateurs;
    }
    default List<UtilisateurDto> utilisateursToDtos(List<Utilisateur> utilisateurs) {
        if(utilisateurs == null) {
            return new ArrayList<>();
        }
        List<UtilisateurDto> utilisateurDtoList = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            utilisateurDtoList.add(this.map(utilisateur));
        }
        return utilisateurDtoList;
    }
}
