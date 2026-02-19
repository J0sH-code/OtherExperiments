package backend.chatbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class responseHandler {
    static HttpResponse<InputStream> response (HttpClient client, HttpRequest req, ollamaAPI ollamaAPI) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = client.send(req, HttpResponse.BodyHandlers.ofInputStream());
        return response;
    }

    static HttpResponse<String> response (HttpClient client, HttpRequest req, geminiAPI geminiAPI) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        return response;
        
    }
}
