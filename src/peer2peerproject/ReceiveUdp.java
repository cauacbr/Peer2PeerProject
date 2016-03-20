/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer2peerproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Cauã
 */
public class ReceiveUdp extends Thread {
    private int port;
    DatagramSocket aSocket = null;

    public ReceiveUdp(int port) {
        this.port = port;
    }
    

    @Override
    public void run() {
        try {
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port
            byte[] buffer = new byte[1000];
            DatagramPacket request;
            while (true) {
                request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
