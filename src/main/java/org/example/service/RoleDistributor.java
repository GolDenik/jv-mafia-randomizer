package org.example.service;

import java.util.Map;
import org.example.model.Roles;

public interface RoleDistributor {
    void assign(Map<Roles, Integer> activeRoleMap);
}
