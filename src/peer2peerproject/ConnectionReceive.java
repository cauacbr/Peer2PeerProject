package peer2peerproject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionReceive extends Thread {

    private String receivedString;

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);

            try {
                Peer2PeerProject.ms.receive(messageIn);
                System.out.println("ConnectionReceive\nRecebendo via multicast");
                receivedString = new String(messageIn.getData());
                String[] saida = receivedString.split("@");
                System.out.println("ConnectionReceive\narraySaida\n" + Arrays.toString(saida));

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
                                + Interface.jTextArea1.getText() + "@";
                        Peer2PeerProject.sendUdp.sendMessage(sendString, messageIn.getAddress(), Integer.valueOf(saida[3]));
                        String mensagem = saida[1] + " entrou, possui " + saida[4] + " bitcoins";
                        Peer2PeerProject.user.setHistorico(mensagem);
                        Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                        System.out.println("ConnectionReceive\n" + mensagem);
                    }
                }

                if (receivedString.startsWith("2")) {
                    if ((!saida[1].equals(Peer2PeerProject.user.getUserName())) && (!saida[2].equals(Peer2PeerProject.user.getUserName()))) {
                        PublicKey p = Peer2PeerProject.user.getUserPublicKey(saida[1]);
//                         String aux1 = saida[3];
//                        byte[] aux = Base64.decode(aux1);
//                        String mensagem = Criptografar.decriptografaPublica(aux, p);
                        String mensagem;
                        //System.out.println("Mensagem descriptografada: " + mensagem);
                        mensagem = saida[1] + " aguarda minerar " + saida[3] + " para " + saida[2];
                        Peer2PeerProject.user.setHistorico(mensagem);
                        Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                        System.out.println("ConnectionReceive\n" + mensagem);
                        Peer2PeerProject.tela.jTextField2.setText((saida[1] + "@" + saida[2] + "@" + saida[3]));
                    }
                }

                if (receivedString.startsWith("3")) {
                    if (saida[1].equals(Peer2PeerProject.user.getUserName())) {
                        Peer2PeerProject.user.setBitcoin(Integer.valueOf(saida[2]));
                        Peer2PeerProject.user.getUserDataByName(saida[3]).setBitcoin(Integer.valueOf(saida[4]));
                        Peer2PeerProject.user.getUserDataByName(saida[5]).setBitcoin(Integer.valueOf(saida[6]));
                    } else if (saida[3].equals(Peer2PeerProject.user.getUserName())) {
                        Peer2PeerProject.user.getUserDataByName(saida[1]).setBitcoin(Integer.valueOf(saida[2]));
                        Peer2PeerProject.user.setBitcoin(Integer.valueOf(saida[4]));
                        Peer2PeerProject.user.getUserDataByName(saida[5]).setBitcoin(Integer.valueOf(saida[6]));
                    } else if (saida[5].equals(Peer2PeerProject.user.getUserName())) {
                        Peer2PeerProject.user.getUserDataByName(saida[1]).setBitcoin(Integer.valueOf(saida[2]));
                        Peer2PeerProject.user.getUserDataByName(saida[3]).setBitcoin(Integer.valueOf(saida[4]));
                        Peer2PeerProject.user.setBitcoin(Integer.valueOf(saida[6]));
                    } else {
                        Peer2PeerProject.user.getUserDataByName(saida[1]).setBitcoin(Integer.valueOf(saida[2]));
                        Peer2PeerProject.user.getUserDataByName(saida[3]).setBitcoin(Integer.valueOf(saida[4]));
                        Peer2PeerProject.user.getUserDataByName(saida[5]).setBitcoin(Integer.valueOf(saida[6]));
                    }

                    String mensagem = "Valores atualizados\n" + saida[1] + " " + saida[2] + " bitcoins\n"
                            + saida[3] + " " + saida[4] + " bitcoins\n"
                            + saida[5] + " " + saida[6] + " bitcoins";
                    Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                    Peer2PeerProject.tela.jTextField2.setText("");
                }
                if (receivedString.startsWith("4")) {
                    String mensagem = saida[1] + " saiu";
                    Peer2PeerProject.user.setHistorico(mensagem);
                    Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + mensagem);
                    Peer2PeerProject.user.removeUsuario(saida[1]);
                    System.out.println("ConnectionReceive\n" + mensagem);
                }

            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            }

            buffer = null;
            messageIn = null;
        }
    }
}
