package jm.security.example.service;

import jm.security.example.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);
}
