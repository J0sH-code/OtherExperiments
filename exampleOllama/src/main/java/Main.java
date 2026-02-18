import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.URI;
import java.net.http.*;

public class Main {
    void main(String[] a) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String prompt = sc.nextLine();

            String json = String.format("""
                    {
                    "model": "llama3",
                    "prompt": "%s",
                    "stream": true
                    }
                    """, prompt);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:11434/api/generate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<InputStream> response =
                    client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));


//        System.out.println(response.body());

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(extractText(line));
            }
            System.out.println();
            System.out.println("Prompt:");

        }
    }

    static String extractText(String response) {
        String search = "\"response\":\"";
        int start = response.indexOf(search) + search.length();
        int end = response.indexOf("\"", start);

        if (start > search.length() - 1 && end > start) {
            return response.substring(start, end);
        }
        else {
            return "\n";
        }
    }
}