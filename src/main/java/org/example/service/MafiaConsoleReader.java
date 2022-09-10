package org.example.service;

import java.util.Map;
import org.example.model.Roles;

public interface MafiaConsoleReader {
    Map<Roles, Integer> read();
}
