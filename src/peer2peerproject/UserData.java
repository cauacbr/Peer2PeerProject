package peer2peerproject;

import java.security.PublicKey;
import java.util.ArrayList;

public class UserData {

    private String userName;
    private int bitcoin = 100;
    private PublicKey publicKey = null;
    private String historico = "";
    int portudp;
    String address;
    private ArrayList<UserData> userData = null;

    public UserData() {

    }

    public UserData(String userName) {
        this.userData = new ArrayList<>();
        this.userName = userName;
        this.publicKey = Criptografar.getPublicKey();
    }

    public void addUserToList(String userName, String address, int portudp, int bitcoin, PublicKey publicKey) {
        UserData newUser = new UserData(userName);
        newUser.setBitcoin(bitcoin);
        newUser.setPublicKey(publicKey);
        newUser.setAddress(address);
        newUser.setPortudp(portudp);
        this.userData.add(newUser);
        //Peer2PeerProject.tela.jList1.setListData(newUser.getUserName());
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

    public int getPortudp() {
        return portudp;
    }

    public void setPortudp(int portudp) {
        this.portudp = portudp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String[] getUserNamesList(){
        String [] names = null;
        System.out.println(Peer2PeerProject.user.userData.size());
        for (int i = 0; i < Peer2PeerProject.user.userData.size(); i++) {
			names [i] = Peer2PeerProject.user.userData.get(i).getUserName();
		} 
        if(names == null){
            names[0] = "";
            return names;
        }
        else{
        return names;
        }
    }

}
