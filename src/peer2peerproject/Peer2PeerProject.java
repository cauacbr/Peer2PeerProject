package peer2peerproject;

import java.net.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.NoSuchPaddingException;

public class Peer2PeerProject {

    public static ConnectionSend cs;
    public static ConnectionReceive cr;
    public static Interface tela;
    public static Criptografar cript;
    public static MulticastSocket ms;
    public static ReceiveUdp receiveUdp;
    public static SendUdp sendUdp;
    public static DatagramSocket udpSocket;
    public static UserData user;

    public static void main(String[] args) throws NoSuchAlgorithmException, UnknownHostException, IOException, NoSuchPaddingException {
        Scanner sc = new Scanner(System.in);
        String ip = "224.224.224.224", userName;
        System.out.print("Digite o nome de usuario: ");
        userName = sc.nextLine();
        user = new UserData(userName);
        int portMult = 6789;
        cript = new Criptografar();
        InetAddress group = InetAddress.getByName(ip);
        ms = new MulticastSocket(portMult);
        ms.joinGroup(group);

        udpSocket = new DatagramSocket();
        receiveUdp = new ReceiveUdp(udpSocket);
        sendUdp = new SendUdp(udpSocket);
        receiveUdp.start();

        cs = new ConnectionSend(group, portMult);
        cr = new ConnectionReceive();
        cr.start();
        tela = new Interface();
        tela.setVisible(true);
        cs.sendFirstMessage();

    }
}
