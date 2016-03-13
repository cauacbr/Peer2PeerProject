package peer2peerproject;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionReceive extends Thread {

    MulticastSocket ms = null;
    String userName, receivedString;

    public ConnectionReceive(MulticastSocket ms, String userName) throws UnknownHostException {
        this.ms = ms;
        this.userName = userName + "@";
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            try {
                ms.receive(messageIn);
                receivedString = new String(messageIn.getData());
            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!receivedString.startsWith(userName)) {
                System.out.println("Received: " + receivedString);
            }
        }
    }
}
