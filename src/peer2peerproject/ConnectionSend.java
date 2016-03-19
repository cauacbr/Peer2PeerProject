package peer2peerproject;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSend {

    private MulticastSocket ms = null;
    private final Scanner sc = new Scanner(System.in);
    private DatagramPacket messageOut;
    private final InetAddress group;
    private final int port;
    Criptografar cript;
    UserData user;

    public ConnectionSend(MulticastSocket ms, InetAddress group, int port, Criptografar cript, UserData user) throws UnknownHostException {
        this.ms = ms;
        this.group = group;
        this.port = port;
        this.cript = cript;
        this.user = user;
    }

    public void sendMessage(String sendString) {
        sendString = user.getUserName() + "@" + sendString;
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendFirstMessage() {
        String sendString = "@first@" + user.getUserName() + "@public@" + user.getPublicKey().toString() + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
