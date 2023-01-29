package gov.iti.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Client {
    static Socket clientSocket;
    static int clientport;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 1891);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream inputStream = clientSocket.getInputStream();
                Scanner in = new Scanner(inputStream, "UTF-8");OutputStream outputStream = clientSocket.getOutputStream()) {

            // DataInputStream dataInputStream = new DataInputStream(inputStream);
            // System.out.println( dataInputStream.readUTF());
            
            // BufferedReader reader = new BufferedReader(new
            // InputStreamReader(inputStream,"UTF-8"));

            PrintWriter out = new PrintWriter((outputStream), true);
            String[] firstMessage = in.nextLine().split(",");
            clientport = Integer.parseInt(firstMessage[1]);
            System.out.println(firstMessage[0]);
            acceptGroupMessage();
            BufferedReader readFromConsle = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String text = readFromConsle.readLine();
                out.println(text);
                //Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static void acceptGroupMessage(){
        new Thread( () -> {
            try (Scanner input = new Scanner(clientSocket.getInputStream(), "UTF-8")) {
                while (true){
                    String data = input.nextLine();
                    String[] splitedData = data.split(",");
                    if(clientport!=Integer.parseInt(splitedData[0]))
                        System.out.println(data);
                    //Thread.sleep(500);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}