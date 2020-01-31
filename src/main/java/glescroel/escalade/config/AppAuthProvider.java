package glescroel.escalade.config;

import glescroel.escalade.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppAuthProvider extends DaoAuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppAuthProvider.class);

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String name = auth.getName();
        String password = auth.getCredentials()
                .toString();
        LOGGER.info(" AppAuthProvider : recherche User = " + name + " / pwd = " + password);

        UserDetails user =  utilisateurService.loadUserByUsername(name);
        LOGGER.info("AppAuthProvider : check " + passwordEncoder.encode(password) + " vs " + user.getPassword());
        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
            LOGGER.info("AppAuthProvider : Utilisateur inconnu");
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        } else {
            LOGGER.info("AppAuthProvider : Utilisateur trouvé : User = " + user.getUsername() + " / pwd = " + user.getPassword());
        }

        return new UsernamePasswordAuthenticationToken(user, auth.getCredentials(), user.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
        LOGGER.info("supports");
        return true;
    }
}