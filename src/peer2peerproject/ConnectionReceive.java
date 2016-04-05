package peer2peerproject;

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
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            try {
                Peer2PeerProject.ms.receive(messageIn);
                receivedString = new String(messageIn.getData());
                Peer2PeerProject.user.setHistorico(receivedString);
                String[] saida = receivedString.split("@");
                if (receivedString.startsWith("1")) {

                    if ("1".equals(saida[0])) {
                        Peer2PeerProject.user.addUserToList(saida[1],
                                saida[2],
                                Integer.parseInt(saida[3]),
                                Integer.parseInt(saida[4]),
                                //Criptografar.stringToPublicKey(saida[5]));
                                Criptografar.getPublicKey());

                    }
                    System.out.println(saida[1] + " entrou");
                    //Peer2PeerProject.tela.jList1.setListData(Peer2PeerProject.user.getUserNamesList());
                }

            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            //if (!receivedString.startsWith(userName + "@")) {
            Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + receivedString);
            Peer2PeerProject.user.setHistorico(receivedString);
            //System.out.println(user.getHistorico());
            //}
        }
    }
}
