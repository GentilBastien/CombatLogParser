package org.bastien.addon.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public abstract class AbstractService {

    protected final HttpClient httpClient;

    public AbstractService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    protected URI getURI() {
        return URI.create("http://localhost:8080");
    }

    protected boolean ensureServerIsRunning() {
        try {
            Socket socket = new Socket();
            // TODO: 20/05/2023 This constructor calls localhost.
            socket.connect(new InetSocketAddress(getURI().getPort()), 4000);
            socket.close();
            return true;
        } catch (IOException e) {
            System.err.println("The server is not running.");
            System.exit(0);
            return false;
        }
    }

    protected String postRequest(String jsonContent) throws IOException, InterruptedException {
        return httpClient.send(
                HttpRequest.newBuilder()
                        .uri(getURI())
                        .header("Content-type", "application/json")
                        .POST(BodyPublishers.ofString(jsonContent))
                        .build(), BodyHandlers.ofString()
        ).body();
    }
}
