package peer2peerproject;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Peer2PeerProject {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner sc = new Scanner(System.in);
        ConnectionSend cs;
        ConnectionReceive cr;
        
        String ip = "224.224.224.224", userName;
        int port = 6789;
        InetAddress group = InetAddress.getByName(ip);
        MulticastSocket ms = new MulticastSocket(port);
        ms.joinGroup(group);
        System.out.print("Digite o nome de usuario: ");
        userName = sc.nextLine();

        cs = new ConnectionSend(ms, userName, group, port);
        cs.start();
        cr = new ConnectionReceive(ms, userName);
        cr.start();
        Interface tela = new Interface(cs);
        tela.setVisible(true);
        

    }
}
