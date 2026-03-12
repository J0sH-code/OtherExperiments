package backend;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class reqHandler implements HttpHandler{
     static DB db = new DB();
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Server is working";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        InputStream resBody = exchange.getRequestBody();
        resBody.toString();

        System.out.println("Request path: " + exchange.getHttpContext().getPath());
        System.out.println("Request local address: " + exchange.getLocalAddress());
        System.out.println("Request Protocol: " + exchange.getProtocol());
        System.out.println("Request Headers: " + exchange.getRequestHeaders().keySet());
        System.out.println("Request method: " + exchange.getRequestMethod());
        System.out.println("Request body: " + new String(resBody.readAllBytes()));
        System.out.println();

        switch (exchange.getRequestMethod()) {
            case "GET" -> getHandler(exchange);
            case "POST" -> postHandler(exchange);
            case "PUT" -> putHandler(exchange);
            case "PATCH" -> patchHandler(exchange);
            case "DELETE" -> deletHandler(exchange);
            default -> throw new AssertionError();
        }

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public static void getHandler(HttpExchange exchange) throws IOException {
        System.out.println(exchange);
        InputStream resBody = exchange.getRequestBody();
        String request = new String(resBody.readAllBytes());
        
        System.out.println(resBody);
        System.out.println(request);
        if ("userList".equals(request)) {
            db.getUserList().forEach((key, value) -> {
                System.out.println("Key: " + key + ", Value: " + value);
            });
        }
        System.out.println(db.getUserList());
    }

    public static void postHandler(HttpExchange exchange) {

    }

    public static void putHandler(HttpExchange exchange) {

    }

    public static void patchHandler(HttpExchange exchange) {

    }

    public static void deletHandler(HttpExchange exchange) {

    }
    
    private void outputHandle(){

    }
}
