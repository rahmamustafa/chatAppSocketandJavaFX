package gov.iti.io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThereadedEchoServerHandler implements Runnable{
    Socket ownSocket;
    public ThereadedEchoServerHandler(Socket incomingSocket){
        ownSocket = incomingSocket;
    }
    @Override
    public void run() {
       try(InputStream inputStream = ownSocket.getInputStream(); Scanner in = new Scanner(inputStream,"UTF-8");OutputStream outputStream = ownSocket.getOutputStream()){
        //DataInputStream dataInputStream = new DataInputStream(inputStream);
        //DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
       
        //BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        
        PrintWriter out = new PrintWriter((outputStream) ,true);
        /*while(!dataInputStream.readUTF().equals("bye")){
            dataOutputStream.writeUTF("Ehco : " + dataInputStream.readUTF());
       }*/
        out.println("Hi in our server," + ownSocket.getPort());
        String clientname = in.nextLine();
        String clientImageUrl = in.nextLine();
        ChatGroupHandling.sendMessage("serverMark," + clientname + " joined the group chat");
        while(in.hasNextLine()){
            String line = in.nextLine(); 
            new Thread(ChatGroupHandling.sendMessage(ownSocket.getPort()+"," +clientname+","+clientImageUrl+","+line)).start();
            //out.println("Echo: " + line);
            System.out.println(line);
            if (line.trim().equals("bye")){
                ChatGroupHandling.sendMessage("serverMark," +clientname + " left");
                ChatGroupHandling.allClientSocket.remove(ownSocket);

                break;
            }
       }
       }catch (IOException e) {
         e.printStackTrace();

        
    }}
}

