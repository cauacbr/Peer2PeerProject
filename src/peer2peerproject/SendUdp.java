/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer2peerproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author Cauã
 */
public class SendUdp {

    DatagramSocket aSocket = null;
    int serverPort;

    public SendUdp(int serverPort) {
        this.serverPort = serverPort;
    }

    public void sendMessage(String sendString, InetAddress host) {
        try {
            aSocket = new DatagramSocket();
            byte[] m = sendString.getBytes();
            InetAddress aHost = InetAddress.getByName(host.getHostName());

            DatagramPacket request =
                     new DatagramPacket(m, sendString.length(), aHost, serverPort);
            aSocket.send(request);
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
