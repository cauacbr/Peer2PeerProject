package peer2peerproject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.net.*;

public class ConnectionSend {

    private DatagramPacket messageOut;
    private final InetAddress group;
    private final int port;

    public ConnectionSend(InetAddress group, int port) throws UnknownHostException {
        this.group = group;
        this.port = port;
    }

    public void sendMineradores(String sendString) throws IOException {
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        Peer2PeerProject.ms.send(messageOut);
        messageOut = null;
        System.out.println("ConnectionSend\nEnviando mensagem de compra");

    }

    public void sendFirstMessage() throws UnknownHostException, IOException {
        String sendString = "1@"
                + Peer2PeerProject.user.getUserName() + "@"
                + InetAddress.getLocalHost().getHostAddress() + "@"
                + Peer2PeerProject.udpSocket.getLocalPort() + "@"
                + Peer2PeerProject.user.getBitcoin() + "@"
                + Base64.encode(Criptografar.getPublicKey().getEncoded()) + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        Peer2PeerProject.ms.send(messageOut);
        messageOut = null;
        System.out.println("ConnectionSend\nEnviando primentira mensagem de login");
    }

    public void sendExitMessage() throws UnknownHostException, IOException {
        String sendString = "4@"
                + Peer2PeerProject.user.getUserName() + "@";
        messageOut = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, group, port);
        Peer2PeerProject.ms.send(messageOut);
        messageOut = null;
        System.out.println("ConnectionSend\nEnviando mensagem de logout");
    }

    public void sendMinerar(String mensagem) throws IOException {
        String[] saida = mensagem.split("@");
        if (Peer2PeerProject.user.getUserDataByName(saida[1]).getBitcoin() > Integer.valueOf(saida[2])) {
            Peer2PeerProject.user.getUserDataByName(saida[1]).setBitcoin(
                    Peer2PeerProject.user.getUserDataByName(saida[1]).getBitcoin() - (Integer.valueOf(saida[2]) + 1));

            Peer2PeerProject.user.setBitcoin((Peer2PeerProject.user.getBitcoin() + 1));

            Peer2PeerProject.user.getUserDataByName(saida[0]).setBitcoin(
                    Peer2PeerProject.user.getUserDataByName(saida[0]).getBitcoin() + (Integer.valueOf(saida[2])));

            mensagem = "Valores atualizados\n" + saida[0] + " " + Peer2PeerProject.user.getUserDataByName(saida[0]).getBitcoin() + " bitcoins\n"
                    + saida[1] + " " + Peer2PeerProject.user.getUserDataByName(saida[1]).getBitcoin() + " bitcoins\n"
                    + Peer2PeerProject.user.getUserName() + " " + Peer2PeerProject.user.getBitcoin() + " bitcoins";
            Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
            Peer2PeerProject.tela.jTextField2.setText("");

            mensagem = "3@" + saida[0] + "@" + Peer2PeerProject.user.getUserDataByName(saida[0]).getBitcoin()
                    + "@" + saida[1] + "@" + Peer2PeerProject.user.getUserDataByName(saida[1]).getBitcoin()
                    + "@" + Peer2PeerProject.user.getUserName() + "@" + Peer2PeerProject.user.getBitcoin() + "@";

            messageOut = new DatagramPacket(mensagem.getBytes(), mensagem.getBytes().length, group, port);
            Peer2PeerProject.ms.send(messageOut);
            messageOut = null;
            System.out.println("ConnectionSend\nEnviando mensagem de compra");
        }
        else{
            Peer2PeerProject.tela.jTextField2.setText("Bitcoins insuficientes");
        }

    }
}
