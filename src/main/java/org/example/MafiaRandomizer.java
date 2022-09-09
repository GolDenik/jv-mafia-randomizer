package org.example;

import java.util.*;

public class MafiaRandomizer {
    private static final List<String> roles = new ArrayList<>();
    private static List<String> checker = new ArrayList<>();
    private static final Random randomizer = new Random();

    static {
        roles.add("DON");
        roles.add("MAFIA1");
        roles.add("MAFIA2");
        roles.add("SHERIF");
        roles.add("MANIAC");
        roles.add("MEDIC");
        roles.add("PROSTITUTE");
    }

    public static void getRoles(int players) {
        checker.clear();
        for (int i = 1; i <= players; i++) {
            System.out.println(i + "  -->  " + getRandomRole(players));
        }
    }

    private static String getRandomRole(int players) {
        int maxCivilians = players - 7;
        int civiliansCounter = 1;
        while (roles.size() != players) {
            roles.add("CIVILIAN" + civiliansCounter++);
        }
        while (true) {
            String random = roles.get(randomizer.nextInt(players));
            if (!checker.contains(random)) {
                checker.add(random);
                return random;
            }
        }
    }
}
