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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.control.*;

public class Main extends Application {

    private TextArea outputArea;
    private TextField inputField;


    @Override
    public void start(Stage primaryStage) {

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


        VBox root = new VBox(10, inputField, enter, outputArea);



        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("JavaFX Ollama Test");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    static void main(String[] a) throws IOException, InterruptedException {

        launch(a);


    }


    private void sendPrompt() throws IOException, InterruptedException {
            String prompt = inputField.getText();

            String escapedPrompt = prompt.replace("\"", "\\\"");
            outputArea.clear();

            new Thread(() -> {
                try {

                    String json = String.format("""
                            {
                            "model": "qwen2.5-coder:0.5b",
                            "prompt": "%s",
                            "stream": true
                            }
                            """.formatted(escapedPrompt), prompt);

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
}