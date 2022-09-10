package org.example;

import java.util.Map;
import org.example.model.Roles;
import org.example.service.MafiaConsoleReader;
import org.example.service.RoleDistributor;
import org.example.service.impl.MafiaConsoleReaderImpl;
import org.example.service.impl.RoleDistributorImpl;

public class Main {
    public static void main(String[] args) {
        MafiaConsoleReader mafiaConsoleReader = new MafiaConsoleReaderImpl();
        Map<Roles, Integer> rolesNumberMap = mafiaConsoleReader.read();

        RoleDistributor roleDistributor = new RoleDistributorImpl();
        roleDistributor.assign(rolesNumberMap);
    }
}
