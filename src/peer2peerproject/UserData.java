package peer2peerproject;

import java.security.PublicKey;
import java.util.ArrayList;

public class UserData {

    private String userName;
    private int bitcoin = 100;
    private PublicKey publicKey = null;
    private String historico = "";
    private ArrayList<UserData> userData;

    public UserData(String userName) {
        this.userData = new ArrayList<>();
        this.userName = userName;
        this.publicKey = Criptografar.getPublicKey();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
