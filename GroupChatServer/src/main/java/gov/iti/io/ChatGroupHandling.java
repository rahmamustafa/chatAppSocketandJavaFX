package gov.iti.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupHandling{
    static List<Socket> allClientSocket = new ArrayList<Socket>();
    static OutputStream outputStream;
    public ChatGroupHandling(){
           
    }
     static Runnable sendMessage(String msg){
        for(Socket client : allClientSocket){
         try {
            outputStream = client.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream,true);
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return null;
     }





}