package gov.iti.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    DatagramSocket dgSocket ;
    public static void main(String args[]) {
        //pass the message, server host and port number as arguments
        try( DatagramSocket dgSocket = new DatagramSocket();) {
            String message = "hi i am client";
            byte[] bytes = message.getBytes();
            InetAddress serverHost = InetAddress.getLocalHost();
            int serverPortNumber = 1000;
            DatagramPacket dgRequest = new DatagramPacket(bytes, 
            message.length(), serverHost, serverPortNumber);
            dgSocket.send(dgRequest);
            byte[] byteBuffer = new byte[1000];
            DatagramPacket dgResponse = new DatagramPacket(byteBuffer, 
            byteBuffer.length);
            dgSocket.receive(dgResponse);
        System.out.println("Datagram Response: " + new
        String(dgResponse.getData()));
        } catch (SocketException e) {
        System.out.println("Socket Exception: " + e.getMessage());
        } catch (IOException e) {
        System.out.println("IO Exception : " + e.getMessage());
        } 
        }
        
}
