package gov.iti.io;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

    
public class ThreadedEchoServer {
   public ThreadedEchoServer(){
    try(ServerSocket serverSocket = new ServerSocket(1891)) {
        
    int i=0;
    System.out.println("Server is waiting");
    while(true){
        Socket socket = serverSocket.accept();
        System.out.println("client number " + ++i + "connected");
        ChatGroupHandling.allClientSocket.add(socket);
        new Thread(new ThereadedEchoServerHandler(socket)).start();
     
    }
    
 } catch (IOException e) {
        e.printStackTrace();
    }
}
   }
   
