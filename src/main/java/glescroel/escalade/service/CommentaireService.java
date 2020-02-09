package glescroel.escalade.service;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.mapper.CommentaireMapper;
import glescroel.escalade.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

    private static final CommentaireMapper COMMENTAIRE_MAPPER = CommentaireMapper.INSTANCE;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public CommentaireDto save(CommentaireDto commentaireDto) {
        return COMMENTAIRE_MAPPER.map(commentaireRepository.save(COMMENTAIRE_MAPPER.map(commentaireDto)));
    }

    public CommentaireDto getCommentaireById(Integer id) {
        return COMMENTAIRE_MAPPER.map(commentaireRepository.getOne(id));
    }

    public void delete(CommentaireDto commentaire) {
        commentaireRepository.delete(COMMENTAIRE_MAPPER.map(commentaire));
    }
}
