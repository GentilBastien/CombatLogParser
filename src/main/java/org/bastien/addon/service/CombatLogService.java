package org.bastien.addon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import org.bastien.addon.model.CombatLog;
import org.bastien.addon.routine.Watcher;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class CombatLogService extends AbstractService {
    @Getter
    private static final CombatLogService instance = new CombatLogService();

    private final ObjectWriter objectWriter;
    private final Collection<CombatLog> buffer;

    private CombatLogService() {
        this.buffer = new ArrayList<CombatLog>();
        final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        CombatLogService s = new CombatLogService();
//        System.out.println(s.getURI());
//        System.out.println(s.ensureServerIsRunning());
//        s.postRequest("{\"name\": \"10\", \"name2\": \"20\"}");
        Watcher.getInstance().setCombatLogDirectory(Path.of("D:\\Users\\basti\\Documents\\Star Wars - The Old Republic\\CombatLogs"));
    }

    public void addToBuffer(CombatLog combatLog) {
        buffer.add(combatLog);
    }

    public void sendFromBuffer() {
        if (buffer.isEmpty()) {
            return;
        }
        try {
            String jsonObj = objectWriter.writeValueAsString(buffer);
            postRequest(jsonObj);
            buffer.clear();
        } catch (JsonProcessingException e) {
            System.err.println("JsonProcessingException -> " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException -> " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("InterruptedException -> " + e.getMessage());
        }
    }

    protected URI getURI() {
        return super.getURI().resolve(URI.create("/combat-logs"));
    }
}
