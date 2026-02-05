package backend;

import java.io.IOException;
import java.net.ServerSocket;

public class myServer {
    static boolean running = true;
    public static void main(String[] args) throws IOException {
        while (running) {
            ServerSocket server = new ServerSocket(8080);
            server.accept();
        }
        
        
    }
}
