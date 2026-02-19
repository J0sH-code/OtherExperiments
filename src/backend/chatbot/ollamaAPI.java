package backend.chatbot;

public class ollamaAPI {

    private final String API_KEY = "Bearer " + System.getenv("OLLAMA_API_KEY");
    private final String keyHeader = "Authorization";
    private final String uri = "https://ollama.com/api/generate";

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
  "model": "qwen3-coder-next:cloud",
  "prompt": "%s",
  "stream": true
}
    """, prompt.replace("\"", "\\\"").replace("\n", "\\n"));
    }
}
