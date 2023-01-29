package gov.iti.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import gov.iti.gui.DB.FakeDB;
import gov.iti.gui.controllers.chatController;
import javafx.application.Platform;

/**
 * Hello world!
 *
 */
public class Client {
    static Socket clientSocket;
    static int clientport;
    InputStream inputStream;
    Scanner in;
    OutputStream outputStream;
    PrintWriter out;
    String[] firstMessage;
    chatController controller;
    String welcomeMessage;

    public Client(chatController cc) {
        try {
            this.controller = cc;
            clientSocket = new Socket("localhost", 1891);
            inputStream = clientSocket.getInputStream();
            in = new Scanner(inputStream, "UTF-8");
            outputStream = clientSocket.getOutputStream();
            out = new PrintWriter((outputStream), true);
            if (in.hasNextLine()) {
                String[] firstMessage = in.nextLine().split(",");
                clientport = Integer.parseInt(firstMessage[1]);
                out.println(FakeDB.getUser().getName());
                out.println(FakeDB.getUser().getImageUrl());

                // System.out.println(firstMessage[0]);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        acceptGroupMessage();
    }

    public void sendMessage(String message) {
        // new Thread(() -> {

        out.println(message);
        // }).start();

    }

    void acceptGroupMessage() {
        new Thread(() -> {
            try {
                Scanner input = new Scanner(clientSocket.getInputStream(), "UTF-8");
                while (input.hasNextLine()) {
                    String data = (String) input.nextLine();
                    if (data.startsWith("serverMark,")){
                        Platform.runLater(() -> controller.welcomeMessage(data.split(",",2)[1]));
                       
                    }
                    else  {
                        String[] splitedData = data.split(",", 4);
                        if (clientport != Integer.parseInt(splitedData[0])) {
                            System.out.println(data);
                            controller.setFriendName(splitedData[1]);
                            controller.setFriendImage(splitedData[2]);
                            Platform.runLater(() -> controller.acceptGroupMessage(splitedData[3]));
                        }
                    }

                    // Thread.sleep(500);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}