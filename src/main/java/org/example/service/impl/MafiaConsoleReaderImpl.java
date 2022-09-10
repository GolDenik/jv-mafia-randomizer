package org.example.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import org.example.model.Roles;
import org.example.service.MafiaConsoleReader;

public class MafiaConsoleReaderImpl implements MafiaConsoleReader {
    private static final int MAFIA_LOW_COEFFICIENT = 4;
    private static final int MAFIA_HIGH_COEFFICIENT = 3;
    private static final int IS_PRESENT = 1;
    private final Map<Roles, Integer> rolesNumberMap = new LinkedHashMap<>();
    private int blackPlayersCounter;

    @Override
    public Map<Roles, Integer> read() {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("- Specify the lines below -");

            System.out.print("Total number of players (at least 3): ");
            int totalPlayersNumber = Integer.parseInt(consoleReader.readLine());
            rolesNumberMap.put(null, totalPlayersNumber);

            System.out.print("Desired number of mafia (recommended "
                    + recommendMafiaNumber(totalPlayersNumber) + "): ");
            int mafiaNumber = Integer.parseInt(consoleReader.readLine());
            rolesNumberMap.put(Roles.MAFIA, mafiaNumber);
            blackPlayersCounter += mafiaNumber;

            System.out.print("Don's presence (enter \"y\" for consent): ");
            int donsPresence = consoleReader.readLine().equals("y") ? IS_PRESENT : 0;
            if (donsPresence == 1) {
                rolesNumberMap.put(Roles.DON, donsPresence);
                blackPlayersCounter += 1;
            }

            System.out.print("Maniac's presence (enter \"y\" for consent): ");
            int maniacsPresence = consoleReader.readLine().equals("y") ? IS_PRESENT : 0;
            if (maniacsPresence == 1) {
                rolesNumberMap.put(Roles.MANIAC, maniacsPresence);
                blackPlayersCounter += 1;
            }

            System.out.print("Hooker's presence (enter \"y\" for consent): ");
            int hookersPresence = consoleReader.readLine().equals("y") ? IS_PRESENT : 0;
            if (hookersPresence == 1) {
                rolesNumberMap.put(Roles.HOOKER, hookersPresence);
            }

            rolesNumberMap.put(Roles.SHERIFF, IS_PRESENT);
            rolesNumberMap.put(Roles.DOCTOR, IS_PRESENT);

            if (totalPlayersNumber <= blackPlayersCounter * 2) {
                throw new RuntimeException("The total number of players is insufficient. "
                        + "Number of black players is greater than civilians.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read lines from the console.");
        }
        return rolesNumberMap;
    }

    private String recommendMafiaNumber(int totalPlayersNumber) {
        int lowNumber = (int) Math.round((double) totalPlayersNumber / MAFIA_LOW_COEFFICIENT);
        int highNumber = (int) Math.round((double) totalPlayersNumber / MAFIA_HIGH_COEFFICIENT);
        return lowNumber != highNumber ? lowNumber + "-" + highNumber : String.valueOf(lowNumber);
    }
}
