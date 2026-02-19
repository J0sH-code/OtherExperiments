package backend.chatbot;

public class geminiAPI {
    private final String API_KEY = "AIzaSyCT4T1DwcjIHMEeci-bN7TTE16WDNJAmjA";
    private final String keyHeader = "x-goog-api-key";
    private final String uri = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent";

    public String getAPI_KEY() {
        return API_KEY;
    }

    public String getKeyHeader() {
        return keyHeader;
    }

    public String getUri() {
        return uri;
    }

    String jsonPrompt(String prompt) {
        return String.format("""
    {
  "contents": [
    {
      "parts": [
        {
          "text": "%s"
        }
      ]
    }
  ],
  "generationConfig": {
    "thinkingConfig": {
          "thinkingBudget": -1
    }
  }
}
    """, prompt.replace("\"", "\\\"").replace("\n", "\\n"));
    }
}
