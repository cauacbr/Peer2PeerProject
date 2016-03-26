package peer2peerproject;

import java.net.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Peer2PeerProject {

    public static void main(String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        ConnectionSend cs;
        ConnectionReceive cr;
        Interface tela;
        String ip = "224.224.224.224", userName = null;
        int portMult = 6789;
        UserData user;
        Criptografar cript = new Criptografar();

        InetAddress group = InetAddress.getByName(ip);
        MulticastSocket ms = new MulticastSocket(portMult);
        ms.joinGroup(group);
        System.out.print("Digite o nome de usuario: ");
        userName = sc.nextLine();
        user = new UserData(userName, group, cript.getPublicKey());

        DatagramSocket udpSocketReceive = new DatagramSocket();
        ReceiveUdp receiveUdp = new ReceiveUdp(udpSocketReceive);
        DatagramSocket udpSocketSend = new DatagramSocket();
        SendUdp sendUdp = new SendUdp(udpSocketSend);
        receiveUdp.start();

        cs = new ConnectionSend(ms, group, portMult, cript, user, udpSocketReceive.getLocalPort());
        cr = new ConnectionReceive(ms, userName, user, cript);
        cr.start();
        tela = new Interface(cs);
        tela.setVisible(true);
        cs.sendFirstMessage();

    }
}
