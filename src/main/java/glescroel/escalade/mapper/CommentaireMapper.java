package glescroel.escalade.mapper;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.model.Commentaire;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {UtilisateurMapper.class})
public interface CommentaireMapper {

    CommentaireMapper INSTANCE = Mappers.getMapper(CommentaireMapper.class);
    UtilisateurMapper UTILISATEUR_MAPPER = Mappers.getMapper(UtilisateurMapper.class);

    @Mappings({
            @Mapping(target = "utilisateur", ignore = true),
            @Mapping(target = "utilisateur.commentaires", ignore = true)
    })
    Commentaire map(CommentaireDto commentaireDto);

    @AfterMapping
    static void addUtilisateur(CommentaireDto commentaireDto, @MappingTarget Commentaire commentaire) {
        commentaire.setUtilisateur(UTILISATEUR_MAPPER.map(commentaireDto.getUtilisateur()));
    }

    @Mappings({
//            @Mapping(target = "utilisateur", ignore = true),
            @Mapping(target = "utilisateur.commentaires", ignore = true)
    })
    CommentaireDto map(Commentaire commentaire);

    @AfterMapping
    static void addUtilisateurDto(Commentaire commentaire, @MappingTarget CommentaireDto commentaireDto) {
        commentaireDto.setUtilisateur(UTILISATEUR_MAPPER.map(commentaire.getUtilisateur()));
    }

    default List<Commentaire> dtosToCommentaires(List<CommentaireDto> commentaireDtoList) {
        if (commentaireDtoList == null) {
            return new ArrayList<>();
        }
        List<Commentaire> commentaires = new ArrayList<>();
        for (CommentaireDto commentaireDto : commentaireDtoList) {
            commentaires.add(this.map(commentaireDto));
        }
        return commentaires;
    }

    default List<CommentaireDto> commentairesToDtos(List<Commentaire> commentaires) {
        if (commentaires == null) {
            return new ArrayList<>();
        }
        List<CommentaireDto> commentaireDtoList = new ArrayList<>();
        for (Commentaire commentaire : commentaires) {
            commentaireDtoList.add(this.map(commentaire));
        }
        return commentaireDtoList;
    }

}
