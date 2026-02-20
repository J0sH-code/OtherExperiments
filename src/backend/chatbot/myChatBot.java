package backend.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.http.*;
import java.util.Scanner;

public class myChatBot {

    static Scanner input = new Scanner(System.in);
    static boolean running = true;
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Select a model* from the list: ");
        System.out.println("""
                Gemini --
                Ollama --
                """);
        System.out.print("Input: ");
        String model = input.nextLine();

        while (running) {
            switch (model.strip().stripIndent().stripLeading().stripTrailing().toLowerCase()) {
                case "gemini" -> runGemini();
                case "ollama" -> runOllama();
                default -> {
                }
            }
        }
    }

    static void runGemini() throws IOException, InterruptedException {
        System.out.print("Prompt: ");
        String prompt = input.nextLine();
        var req = requestHandler.sendRequest(new geminiAPI(), prompt);
        var res = responseHandler.response(client, req, new geminiAPI());

        System.out.println();
        System.out.println("Response: " + messageHandler.extractText(new geminiAPI(), res.body()));
    }

    static void runOllama() throws IOException, InterruptedException {
        System.out.print("Prompt: ");
        String prompt = input.nextLine();
        var req = requestHandler.sendRequest(new ollamaAPI(), prompt);
        var res = responseHandler.response(client, req, new ollamaAPI());

        BufferedReader reader = new BufferedReader(new InputStreamReader(res.body()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.print(messageHandler.extractText(new ollamaAPI(), line));

        }
        System.out.println();
    }
}
