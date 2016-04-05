package peer2peerproject;

import java.io.IOException;
import java.net.*;
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
                if(receivedString.startsWith("1")){
                    UserData recvUser;
                    for(int i = 0; i < receivedString.length(); i++){
                        
                    }
                    System.out.println("Entrou");
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
