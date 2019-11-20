package glescroel.escalade.mapper;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    Utilisateur map(UtilisateurDto utilisateurDto);
    UtilisateurDto map(Utilisateur utilisateur);

    List<Utilisateur> dtosToUtilisateurs(List<UtilisateurDto> utilisateurDtos);
    List<UtilisateurDto> utilisateursToDtos(List<Utilisateur> utilisateurs);
}
