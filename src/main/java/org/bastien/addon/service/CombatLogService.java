package org.bastien.addon.service;

import org.bastien.addon.routine.Watcher;

import java.net.URI;
import java.nio.file.Path;

public class CombatLogService extends AbstractService {

    public static void main(String[] args) {
//        CombatLogService s = new CombatLogService();
//        System.out.println(s.getURI());
//        System.out.println(s.ensureServerIsRunning());
//        s.postRequest("{\"name\": \"10\", \"name2\": \"20\"}");
        Watcher.getInstance().setCombatLogDirectory(Path.of("D:\\Users\\basti\\Documents\\Star Wars - The Old Republic\\CombatLogs"));
    }

    protected URI getURI() {
        return super.getURI().resolve(URI.create("/combat-logs"));
    }
}
