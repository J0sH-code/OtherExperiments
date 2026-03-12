package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class scratchHttp {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server listening on port 8080 ");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            var out = clientSocket.getOutputStream();

            System.out.println(in.lines());
            String requestLine = in.readLine();
            if (requestLine != null) {
                System.out.println("Request: " + requestLine);
            }


        }
    }
}
