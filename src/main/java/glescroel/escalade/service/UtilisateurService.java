package glescroel.escalade.service;

import glescroel.escalade.dto.UtilisateurDto;
import glescroel.escalade.mapper.UtilisateurMapper;
import glescroel.escalade.model.Utilisateur;
import glescroel.escalade.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UtilisateurService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    private static final UtilisateurMapper UTILISATEUR_MAPPER = UtilisateurMapper.INSTANCE;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurDto getUtilisateurByNom(String nom){
        Optional<Utilisateur> result = utilisateurRepository.findByNomIgnoreCase(nom);
        UtilisateurDto utilisateurDto = null;
        if (result.isPresent()){
            utilisateurDto = UTILISATEUR_MAPPER.map(result.get());
        }
        return utilisateurDto;
    }

    public UtilisateurDto getUtilisateurByEmail(String email){
        Optional<Utilisateur> result = utilisateurRepository.findByEmailIgnoreCase(email);
        UtilisateurDto utilisateurDto = null;
        if (result.isPresent()){
            utilisateurDto = UTILISATEUR_MAPPER.map(result.get());
        }
        return utilisateurDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Objects.requireNonNull(email);
        LOGGER.info("UtilisateurService : loadUserByUsername : username=" + email);
        Utilisateur user = utilisateurRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        LOGGER.info("UtilisateurService : loadUserByUsername : trouvé : " + user.getNom() + " / id = " + user.getId());
        return user;
    }

    public UtilisateurDto save(UtilisateurDto utilisateur) {
        return UTILISATEUR_MAPPER.map(utilisateurRepository.save(UTILISATEUR_MAPPER.map(utilisateur)));
    }

    public UtilisateurDto getUtilisateurById(Integer id) {
        Optional<Utilisateur> result = utilisateurRepository.findById(id);
        UtilisateurDto utilisateurDto = null;
        if (result.isPresent()){
            utilisateurDto = UTILISATEUR_MAPPER.map(result.get());
        }
        return utilisateurDto;
    }

    public void remove(UtilisateurDto utilisateur) {
        utilisateurRepository.delete(UTILISATEUR_MAPPER.map(utilisateur));
    }
}
