package gov.iti.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main( String[] args )
    {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("server is waiting .. . ");
            Socket server = serverSocket.accept();
            OutputStream outputStream = server.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
