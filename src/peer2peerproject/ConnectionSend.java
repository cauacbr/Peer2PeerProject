package peer2peerproject;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionSend {

    private DatagramPacket messageOut;
    private final InetAddress group;
    private final int port;

    public ConnectionSend(InetAddress group, int port) throws UnknownHostException {
        this.group = group;
        this.port = port;
    }

    public void sendMessage(String sendString) {
        sendString = Peer2PeerProject.user.getUserName() + "@" + sendString;
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            Peer2PeerProject.ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendFirstMessage() throws UnknownHostException, IOException {
        String sendString = "1" + "@"
                + Peer2PeerProject.user.getUserName() + "@"
                + InetAddress.getLocalHost().getHostAddress() + "@"
                + Peer2PeerProject.udpSocket.getLocalPort() + "@"
                + Peer2PeerProject.user.getBitcoin() + "@"
                + Criptografar.getPublicKey();
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        Peer2PeerProject.ms.send(messageOut);
    }

    /*public void sendUserInfo() throws UnknownHostException {
        String sendString = "@first@" + "@adress@" + InetAddress.getLocalHost()
                + Peer2PeerProject.user.getUserName() + "@portudp@" + Peer2PeerProject.udpSocket.getLocalPort() + "@coin@" + Peer2PeerProject.user.getBitcoin()
                + "@hist@" + Peer2PeerProject.user.getHistorico() + "@publickey@" + Peer2PeerProject.user.getPublicKey().toString() + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        try {
            Peer2PeerProject.ms.send(messageOut);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSend.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}
