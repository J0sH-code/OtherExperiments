package backend.chatbot;

public class messageHandler {

    static String extractText(geminiAPI geminiAPI, String jsonResponse) {
        String searchKey = "\"text\": \"";
        int start = jsonResponse.indexOf(searchKey);

        if (start == -1) {
            return "Response key not found";
        }

        start += searchKey.length();
        // Finds the next quote that is NOT preceded by a backslash
        int end = start;
        while (end < jsonResponse.length()) {
            end = jsonResponse.indexOf("\"", end);
            if (jsonResponse.charAt(end - 1) != '\\') {
                break;
            }
            end++;
        }

        return jsonResponse.substring(start, end)
                .replace("\\n", "\n")
                .replace("\\\"", "\"");
    }

    ;
    
    static String extractText(ollamaAPI ollamaAPI, String jsonResponse) {
        String searchKey = "\"response\":\"";
        int start = jsonResponse.indexOf(searchKey);

        if (start == -1) {
            return "Response key not found";
        }

        start += searchKey.length();
        // Finds the next quote that is NOT preceded by a backslash
        int end = start;
        while (end < jsonResponse.length()) {
            end = jsonResponse.indexOf("\"", end);
            if (jsonResponse.charAt(end - 1) != '\\') {
                break;
            }
            end++;
        }

        return jsonResponse.substring(start, end)
                .replace("\\n", "\n")
                .replace("\\\"", "\"");
    }
}
