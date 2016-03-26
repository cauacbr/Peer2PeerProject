/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer2peerproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cauã
 */
public class ReceiveUdp extends Thread {

    DatagramSocket udpSocketReceive;

    public ReceiveUdp(DatagramSocket udpSocketReceive) {
        this.udpSocketReceive = udpSocketReceive;
    }

    @Override
    public void run() {
        // create socket at agreed port        
        DatagramPacket request;
        while (true) {
            byte[] buffer = new byte[1000];
            request = new DatagramPacket(buffer, buffer.length);
            try {
                udpSocketReceive.receive(request);
                String receivedString = new String(request.getData());
                System.out.println(receivedString);
            } catch (IOException ex) {
                Logger.getLogger(ReceiveUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
