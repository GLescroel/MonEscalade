package glescroel.escalade.service;

import glescroel.escalade.model.Role;
import glescroel.escalade.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleByName(String nomRole) {
        return roleRepository.getRoleByRole(nomRole);
    }
}
