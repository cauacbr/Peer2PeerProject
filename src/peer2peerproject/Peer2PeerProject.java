package peer2peerproject;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Peer2PeerProject {

    public static void main(String[] args) {
        MulticastSocket ms = null;
        Scanner sc = new Scanner(System.in);
        ConnectionReceive cr;
        DatagramPacket messageOut;
        String userName;
        System.out.print("Digite o nome de usuario: ");
        userName = sc.nextLine();
        try {
            InetAddress group = InetAddress.getByName("224.224.224.224");
            ms = new MulticastSocket(6789);
            ms.joinGroup(group);
            cr = new ConnectionReceive(ms, userName);
            cr.start();
            while (true) {
                System.out.print("Digite a mensagem: ");
                String m = sc.nextLine();
                m = userName + "@" + m;
                messageOut = new DatagramPacket(m.getBytes(), m.getBytes().length, group, 6789);
                ms.send(messageOut);
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Peer2PeerProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //s.leaveGroup(group);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (ms != null) {
                ms.close();
            }
        }
    }

}
