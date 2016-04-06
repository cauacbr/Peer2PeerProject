package peer2peerproject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionReceive extends Thread {

    private String receivedString;

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[3000];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            try {
                Peer2PeerProject.ms.receive(messageIn);
                receivedString = new String(messageIn.getData());
                Peer2PeerProject.user.setHistorico(receivedString);
                String[] saida = receivedString.split("@");

                if (receivedString.startsWith("1")) {
                    if ((!saida[1].equals(Peer2PeerProject.user.getUserName())) && !(Peer2PeerProject.user.verificaUsuario(saida[1]))) {
                        Peer2PeerProject.user.addUserToList(saida[1],
                                saida[2],
                                Integer.parseInt(saida[3]),
                                Integer.parseInt(saida[4]),
                                Criptografar.stringToPublicKey(saida[5]));
                        Peer2PeerProject.tela.jList1.setListData(Peer2PeerProject.user.getUserNamesList());

                        String sendString = "1" + "@"
                                + Peer2PeerProject.user.getUserName() + "@"
                                + InetAddress.getLocalHost().getHostAddress() + "@"
                                + Peer2PeerProject.udpSocket.getLocalPort() + "@"
                                + Peer2PeerProject.user.getBitcoin() + "@"
                                + Base64.encode(Criptografar.getPublicKey().getEncoded()) + "@"
                                + Peer2PeerProject.user.getHistorico() + "@";
                        Peer2PeerProject.sendUdp.sendMessage(sendString, messageIn.getAddress(), Integer.valueOf(saida[3]));

                        String mensagem = saida[1] + " entrou, possui " + saida[4] + " bitcoins";
                        Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                    }

                }

                //System.out.println(user.getHistorico());
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
