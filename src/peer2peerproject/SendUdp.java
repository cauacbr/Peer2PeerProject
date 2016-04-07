/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer2peerproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Cauã
 */
public class SendUdp {

    DatagramSocket udpSocketSend;

    public SendUdp(DatagramSocket udpSocketSend) {
        this.udpSocketSend = udpSocketSend;
    }

    public void sendMessage(String sendString, InetAddress host, int serverPort) throws UnknownHostException, IOException, InterruptedException {

        byte[] m = sendString.getBytes();

        DatagramPacket request = new DatagramPacket(m, sendString.length(), InetAddress.getByName(host.getHostName()), serverPort);
        System.out.println("SendUdp\nsendMessage\nEnviando via UDP: " + sendString + "\n" + System.currentTimeMillis());
        Thread.sleep((long) (Math.random() * 10));
        udpSocketSend.send(request);
        System.out.println("SendUdp\nsendMessage\nEnviado " + System.currentTimeMillis());

        m = null;
        request = null;
    }

    public void sendBuyMessageUdp(String valor, String vendedor, InetAddress host, int serverPort) throws UnknownHostException, IOException {
        String sendString = "2@" + Peer2PeerProject.user.getUserName() + "@" + vendedor+ "@" + valor + "@";
        byte[] m = sendString.getBytes();
        DatagramPacket request = new DatagramPacket(m, sendString.length(), InetAddress.getByName(host.getHostName()), serverPort);
        System.out.println("SendUdp\nsendBuyMessage\nEnviando via UDP: " + sendString + "\n");
        Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n"
                + Peer2PeerProject.user.getUserName() + " compra de " + vendedor + " " + valor + " pendente");
        udpSocketSend.send(request);
        System.out.println("SendUdp\nsendBuyMessage\nEnviado ");

        m = null;
        request = null;
    }

}
