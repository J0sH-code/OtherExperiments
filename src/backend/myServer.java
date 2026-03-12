package backend;

import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class myServer {
    public static void main(String[] args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(3000), 0);

        server.createContext("/test", new reqHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running");
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
