package backend.chatbot;

import java.net.URI;
import java.net.http.*;

public class requestHandler {
    static HttpRequest sendRequest (geminiAPI geminiAPI, String prompt) {
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(geminiAPI.getUri()))
                    .header(geminiAPI.getKeyHeader(), geminiAPI.getAPI_KEY())
                    .header("'Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(geminiAPI.jsonPrompt(prompt)))
                    .build();
        return request;
    }

    static HttpRequest sendRequest (ollamaAPI ollamaAPI, String prompt) {
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ollamaAPI.getUri()))
                    .header(ollamaAPI.getKeyHeader(), ollamaAPI.getAPI_KEY())
                    .header("'Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(ollamaAPI.jsonPrompt(prompt)))
                    .build();
        return request;
    }
}
