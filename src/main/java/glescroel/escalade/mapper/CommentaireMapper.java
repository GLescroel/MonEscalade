package glescroel.escalade.mapper;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.model.Commentaire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentaireMapper {

    CommentaireMapper INSTANCE = Mappers.getMapper(CommentaireMapper.class);

    Commentaire map(CommentaireDto commentaireDto);
    CommentaireDto map(Commentaire commentaire);

    List<Commentaire> dtosToCommentaires(List<CommentaireDto> commentaireDtos);
    List<CommentaireDto> commentairesToDtos(List<Commentaire> commentaires);
}
