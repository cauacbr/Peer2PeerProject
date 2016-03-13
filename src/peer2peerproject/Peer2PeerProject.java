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
        int port = 6789;
        UserData user;
        Criptografar cript = new Criptografar();

        InetAddress group = InetAddress.getByName(ip);
        MulticastSocket ms = new MulticastSocket(port);
        ms.joinGroup(group);
        System.out.print("Digite o nome de usuario: ");
        userName = sc.nextLine();
        user = new UserData(userName, group, cript.getPublicKey());

        cs = new ConnectionSend(ms, userName, group, port, cript, user);
        cs.start();
        cr = new ConnectionReceive(ms, userName, user, cript);
        cr.start();
        tela = new Interface(cs);
        tela.setVisible(true);

    }
}
