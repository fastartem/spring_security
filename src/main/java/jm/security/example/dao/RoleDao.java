package jm.security.example.dao;

import jm.security.example.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);

    Long getIdByRole(String role);
}
