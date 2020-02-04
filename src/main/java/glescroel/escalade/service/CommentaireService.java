package glescroel.escalade.service;

import glescroel.escalade.dto.CommentaireDto;
import glescroel.escalade.mapper.CommentaireMapper;
import glescroel.escalade.model.Commentaire;
import glescroel.escalade.repository.CommentaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentaireService.class);

    private static final CommentaireMapper COMMENTAIRE_MAPPER = CommentaireMapper.INSTANCE;

    @Autowired
    private CommentaireRepository commentaireRepository;

    public CommentaireDto save(CommentaireDto commentaireDto) {
        LOGGER.info("commentaireService : DTO idUtilisateur = " + commentaireDto.getUtilisateur().getId());
        Commentaire commentaire = COMMENTAIRE_MAPPER.map(commentaireDto);
        LOGGER.info("commentaireService : ENTITY idUtilisateur = " + commentaire.getUtilisateur().getId());
        return COMMENTAIRE_MAPPER.map(commentaireRepository.save(COMMENTAIRE_MAPPER.map(commentaireDto)));
    }
}
