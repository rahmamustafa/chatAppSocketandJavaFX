package gov.iti.io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SipmleUDPServer {
    public static void main(String args[]) {
        try (DatagramSocket dgSocket = new DatagramSocket(1000)) {
            byte[] byteBuffer;
            String message = "rahma";
            byteBuffer = message.getBytes();
            while (true) {
                DatagramPacket dgRequest = new DatagramPacket(byteBuffer, message.length());
                dgSocket.receive(dgRequest);
                DatagramPacket dgresponse = new DatagramPacket(dgRequest.getData(),
                        dgRequest.getLength(),
                        dgRequest.getAddress(), dgRequest.getPort());
                dgSocket.send(dgresponse);
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
