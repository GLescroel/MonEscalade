package glescroel.escalade.service;

import glescroel.escalade.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

}
