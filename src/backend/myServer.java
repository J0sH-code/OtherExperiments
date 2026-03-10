package backend;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

public class myServer {
    public static void main(String[] args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(3000), 0);

        server.createContext("/test", new reqHandler());
        server.setExecutor(null);
        server.start();
    }
}

class reqHandler implements HttpHandler {
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

        if (exchange.getRequestMethod().equals("GET")) {
            getHandler(exchange);
        }

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    public static void getHandler(HttpExchange exchange) {
        try {
            var request = new String(exchange.getRequestBody().readAllBytes());
            if ("userList".equals(request)) {
                
            }
            System.out.println(db.getUserList());
        } catch (IOException e) {

        }

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

class User {
    private String name;
    private int age;
    private String occupation;

    public User(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return occupation;
    }
}

class DB {
    private HashMap<String, User> userList = new HashMap();

    public DB() {
        userList.put("Josh", new User("Josh", 18, "Student"));
        userList.put("Zoey", new User("Zoey", 18, "Student"));
    }

    public HashMap<String, User> getUserList() {
        return userList;
    }
    
    public void add(String key, User user) {
        userList.put(key, user);
    }

    public User search(String username) {
        if (userList.containsKey(username)) {
            return userList.get(username);
        } 
        System.out.println("Username not found");
        return null; 
    }
}
