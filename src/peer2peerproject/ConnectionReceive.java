package peer2peerproject;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionReceive extends Thread {

    MulticastSocket ms = null;
    String userName, receivedString;
    UserData user;
    Criptografar cript;

    public ConnectionReceive(MulticastSocket ms, String userName, UserData user, Criptografar cript) throws UnknownHostException {
        this.ms = ms;
        this.userName = userName;
        this.user = user;
        this.cript = cript;
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1000];
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            try {
                ms.receive(messageIn);
                receivedString = new String(messageIn.getData());
                user.setHistorico(receivedString);
                if(receivedString.startsWith("@first@")){
                    System.out.println("Entrou");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ConnectionReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            //if (!receivedString.startsWith(userName + "@")) {
            Interface.jTextArea1.setText(Interface.jTextArea1.getText() + "\n" + receivedString);
            user.setHistorico(receivedString);
            //System.out.println(user.getHistorico());
            //}
        }
    }
}
