package peer2peerproject;

import java.net.InetAddress;
import java.security.PublicKey;
import java.util.ArrayList;

public class UserData {

    private String userName;
    private final InetAddress address;
    private int bitcoin = 100;
    private PublicKey publicKey = null;
    private String historico = "";
    private ArrayList<UserData> userData;

    public UserData(String userName, InetAddress address, PublicKey publicKey) {
        this.userData = new ArrayList<>();
        this.userName = userName;
        this.address = address;
        this.publicKey = publicKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public InetAddress getAddress() {
        return address;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = this.historico + "\n" + historico;
    }

    public ArrayList<UserData> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<UserData> userData) {
        this.userData = userData;
    }

    public int getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(int bitcoin) {
        this.bitcoin = bitcoin;
    }
}
