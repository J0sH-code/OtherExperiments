package backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class myServer {
    public static void main(String[] args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(3000), 0);

        server.createContext("/test", new reqHandler());
        server.setExecutor(null); 
        server.start();
    }

    static class reqHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Server is working";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            InputStream resBody = exchange.getRequestBody();
            resBody.toString();

            System.out.println("Request local address: " + exchange.getLocalAddress());
            System.out.println("Request Protocol: " + exchange.getProtocol());
            System.out.println("Request Headers: " + exchange.getRequestHeaders().keySet());
            System.out.println("Request method: " + exchange.getRequestMethod());
            System.out.println("Request body: " + new String(resBody.readAllBytes()));
            System.out.println();
            
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        public static void getHandler(HttpExchange exchange) {

        }

        public static void postHandler(HttpExchange exchange) {

        }

        public static void putHandler(HttpExchange exchange) {

        }

        public static void patchHandler(HttpExchange exchange) {

        }

        public static void deletHandler(HttpExchange exchange) {

        }
    }
}
