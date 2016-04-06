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
import java.net.UnknownHostException;

/**
 *
 * @author Cauã
 */
public class SendUdp {

    DatagramSocket udpSocketSend;

    public SendUdp(DatagramSocket udpSocketSend) {
        this.udpSocketSend = udpSocketSend;
    }

    public void sendMessage(String sendString, InetAddress host, int serverPort) throws UnknownHostException, IOException, InterruptedException {

        byte[] m = sendString.getBytes();
        InetAddress aHost = InetAddress.getByName(host.getHostName());

        DatagramPacket request
                = new DatagramPacket(m, sendString.length(), aHost, serverPort);

        Thread.sleep((long) (Math.random() * 10));
        udpSocketSend.send(request);

    }

}
