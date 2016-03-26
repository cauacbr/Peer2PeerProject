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
    int udpport;

    public ConnectionSend(MulticastSocket ms, InetAddress group, int port, Criptografar cript, UserData user, int udpport) throws UnknownHostException {
        this.ms = ms;
        this.group = group;
        this.port = port;
        this.cript = cript;
        this.user = user;
        this.udpport = udpport;
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

    public void sendFirstMessage() throws UnknownHostException {
        String sendString = "@first@" + "@adress@" + InetAddress.getLocalHost() +
                user.getUserName() + "@portudp@" + udpport + "@coin@" + user.getBitcoin()
                + "@publickey@" + user.getPublicKey().toString() + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendUserInfo() throws UnknownHostException {
        String sendString = "@first@" + "@adress@" + InetAddress.getLocalHost() +
                user.getUserName() + "@portudp@" + udpport + "@coin@" + user.getBitcoin()
                + "@hist@" + user.getHistorico() + "@publickey@" + user.getPublicKey().toString() + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
