package exampleOllama;



import java.util.concurrent.ExecutionException;
import java.util.regex.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.net.URI;
import java.net.http.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;

public class Main extends Application {

    private ComboBox<String> modelSelector;
    private TextArea outputArea;
    private TextField inputField;


    @Override
    public void start(Stage primaryStage) {

        modelSelector = new ComboBox<>();
        modelSelector.getItems().addAll(
                "qwen2.5-coder:0.5b",
                "llama3",
                "dolphin-mistral",
                "phi"
        );
        modelSelector.setValue("qwen2.5-coder:0.5b");

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        inputField = new TextField();
        inputField.setPromptText("Hello");

        Button enter = new Button("Enter Prompt");

        enter.setOnAction(e -> {
            try {
                sendPrompt();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });


        VBox root = new VBox(10, inputField, enter, outputArea, modelSelector);



        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("JavaFX Ollama Test");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    static void main(String[] a) throws IOException, InterruptedException {

        launch(a);


    }
//    static String jsonFormatter(String text) {
//        return "\"" + text
//                .replace("\\", "\\\\")
//                .replace("\"", "\\\"")
//                .replace("\n", "\\n")
//                .replace("\r", "\\r")
//                .replace("\t", "\\t")
//                + "\"";
//    }

    private void sendPrompt() throws IOException, InterruptedException {
            String prompt = inputField.getText();
            String selectedModel = modelSelector.getValue();

            outputArea.clear();

            new Thread(() -> {
                try {

                    String json = """
                            {
                            "model": "%s",
                            "prompt": "%s",
                            "stream": true
                            }
                            """.formatted(selectedModel, prompt);

                    HttpClient client = HttpClient.newHttpClient();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:11434/api/generate"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();

                    HttpResponse<InputStream> response =
                            client.send(request, HttpResponse.BodyHandlers.ofInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));


                    System.out.println(response.body());

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String text = extractText(line);
                        Platform.runLater(() -> {
                            outputArea.appendText(text);
                                });
                        System.out.print(extractText(line));
                    }
                    System.out.println();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();

    }

    static String extractText(String line) {
        try {
            Pattern pattern = Pattern.compile("\"response\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String raw = matcher.group(1);

                return raw
                        .replace("\\\\", "\\")
                        .replace("\\\"", "\"")
                        .replace("\\n", "\n")
                        .replace("\\r", "\r")
                        .replace("\\t", "\t");
            }

        } catch (Exception ignored) {
           ignored.printStackTrace();
        }

        return "";
    }
}



//    static String extractText(String response) {
//        try {
//            int start = response.indexOf("\"response\":\"");
//            if (start == - 1) return " ";
//
//            start += 12;
//
//            StringBuilder result = new StringBuilder();
//            boolean escaping = false;
//
//            for (int i = start; i < response.length(); i++) {
//                char character = response.charAt(i);
//
//                if (escaping) {
//
//                    switch (character) {
//                        case 'n' -> result.append('\n');
//                        case 'r' -> result.append('\r');
//                        case 't' -> result.append('\t');
//                        case '"' -> result.append('"');
//                        case '\\' -> result.append('\\');
//                        default -> result.append(character);
//                    }
//                } else if (character == '\\') {
//                    escaping = true;
//                } else if (character == '"') {
//                    break;
//                } else {
//                    result.append(character);
//                }
//            }
//
//
//                return result.toString();
//
//            } catch (Exception exempt) {
//            return "";

//        String search = "\"response\":\"";
//        int start = response.indexOf(search) + search.length();
//        int end = response.indexOf("\"", start);
//
//        if (start > search.length() - 1 && end > start) {
//            return response.substring(start, end);
//        }
//        else {
//            return "\n";
//        }
//    }
    //    static String expressions(String line){
    //        Pattern pattern = Pattern.compile("\"[^<]+\"", Pattern.CASE_INSENSITIVE);
    //        Matcher matcher = pattern.matcher(line);
    //
    //        StringBuilder result = new StringBuilder();
    //
    //        while(matcher.find()){
    //            result.append(matcher.group());
    //
    //            if (matcher.group().endsWith("\"")) matcher.find();
    //        }
    //        return result.toString();
    //    }