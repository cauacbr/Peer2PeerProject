package peer2peerproject;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSend extends Thread {

    private MulticastSocket ms = null;
    private String userName;
    private Scanner sc = new Scanner(System.in);
    private DatagramPacket messageOut;
    private InetAddress group;
    private int port;

    public ConnectionSend(MulticastSocket ms, String userName, InetAddress group, int port) throws UnknownHostException {
        this.ms = ms;
        this.userName = userName + "@";
        this.group = group;
        this.port = port;
    }

    public void sendMessage(String sendString) {
        System.out.print("Digite a mensagem: ");
        sendString = userName + "@" + sendString;
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Peer2PeerProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
    }
}
