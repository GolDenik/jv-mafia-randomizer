package org.example.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.example.model.Roles;
import org.example.service.RoleDistributor;

public class RoleDistributorImpl implements RoleDistributor {
    private final Map<Integer, Roles> fullRoleMap = new HashMap<>();
    private final Set<Integer> occupiedRole = new HashSet<>();
    private final Random random = new Random();
    private int totalPlayers;

    @Override
    public void assign(Map<Roles, Integer> rolesNumberMap) {
        totalPlayers = rolesNumberMap.get(null);
        rolesNumberMap.remove(null);
        if (rolesNumberMap.size() > totalPlayers) {
            throw new RuntimeException("The number of roles is greater than the number of player."
                    + System.lineSeparator()
                    + rolesNumberMap + " for " + totalPlayers + " players.");
        }
        for (Map.Entry<Roles, Integer> entry : rolesNumberMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                while (true) {
                    int randomFromTotalNumber = random.nextInt(totalPlayers) + 1;
                    if (!occupiedRole.contains(randomFromTotalNumber)) {
                        fullRoleMap.put(randomFromTotalNumber, entry.getKey());
                        occupiedRole.add(randomFromTotalNumber);
                        break;
                    }
                }
            }
        }
        print();
    }

    private void print() {
        System.out.println("================================================");
        for (int i = 1; i <= totalPlayers; i++) {
            System.out.println("Player " + i + " - " + fullRoleMap.getOrDefault(i, Roles.CIVILIAN));
        }
    }
}
