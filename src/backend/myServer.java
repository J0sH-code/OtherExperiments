package backend;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

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


            System.out.println("Response local address: " + exchange.getLocalAddress());
            System.out.println("Response Protocol: " + exchange.getProtocol());
            System.out.println();
            
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class getHandler {

    }

    static class postHandler {

    }

    static class patchHandler {

    }

    static class putHandler {

    }

    static class deleteHandler {
        
    }
}
