/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peer2peerproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cauã
 */
public class ReceiveUdp extends Thread {

    DatagramSocket udpSocketReceive;

    public ReceiveUdp(DatagramSocket udpSocketReceive) {
        this.udpSocketReceive = udpSocketReceive;
    }

    @Override
    public void run() {
        // create socket at agreed port        
        DatagramPacket request;
        while (true) {
            byte[] buffer = new byte[2048];
            request = new DatagramPacket(buffer, buffer.length);
            try {
                udpSocketReceive.receive(request);
                String receivedString = new String(request.getData());
                Peer2PeerProject.user.setHistorico(receivedString);
                String[] saida = receivedString.split("@");
                System.out.println("ReceiveUdp\nRecebido via UDP de " + saida[1]);
                System.out.println("ReceiveUdp\narraySaida\n" + Arrays.toString(saida));

                if (receivedString.startsWith("1")) {
                    if ((!saida[1].equals(Peer2PeerProject.user.getUserName())) && !(Peer2PeerProject.user.verificaUsuario(saida[1]))) {
                        Peer2PeerProject.user.addUserToList(saida[1],
                                saida[2],
                                Integer.parseInt(saida[3]),
                                Integer.parseInt(saida[4]),
                                Criptografar.stringToPublicKey(saida[5]));
                        Peer2PeerProject.user.setHistorico(saida[6]);                        
                        String mensagem = saida[1] + " entrou, possui " + saida[4] + " bitcoins";
                        System.out.println("ReceiveUdp\n" + mensagem);
                        Peer2PeerProject.user.setHistorico(mensagem);  
                        Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                        Peer2PeerProject.tela.jList1.setListData(Peer2PeerProject.user.getUserNamesList());
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ReceiveUdp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(ReceiveUdp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ReceiveUdp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            buffer = null;
            request = null;
        }
    }
}
