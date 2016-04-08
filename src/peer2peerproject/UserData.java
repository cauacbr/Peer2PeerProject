package peer2peerproject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

//carteira do usuario
public class UserData {

    private String userName;
    private int bitcoin = 100;
    public PublicKey publicKey = null;
    private String historico = "";
    int portudp;
    String address;
    public ArrayList<UserData> userData = null;

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
        System.out.println("UserData\nAdicionando novo usuario: " + userName);
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
        System.out.println("UserData\nsetHistorico\nHistorico: " + this.historico);
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

    public boolean verificaUsuario(String nome) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                System.out.println("UserData\nverificaUsuario\nVerifica usuario: " + this.userData.get(i).getUserName() + " true");
                return true;
            }
        }
        System.out.println("UserData\nverificaUsuario\nVerifica usuario: " + nome + " false");
        return false;
    }

    public String[] getUserNamesList() {
        List<String> names = new ArrayList<>();
        System.out.println("UserData\nListando nomes de usuarios\ngetUserNamesList()");
        for (int i = 0; i < this.userData.size(); i++) {
            names.add(this.userData.get(i).getUserName());
            System.out.println(this.userData.get(i).getUserName());

        }

        String[] names1 = new String[names.size()];
        for (int i = 0; i < names.size(); i++) {
            names1[i] = names.get(i);
        }

        if (names == null) {
            names1[0] = "";
            return names1;
        } else {
            return names1;
        }
    }

    public void removeUsuario(String nome) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                this.userData.remove(i);
                Peer2PeerProject.tela.jList1.setListData(Peer2PeerProject.user.getUserNamesList());
            }
        }
    }

    public InetAddress getUserAddress(String nome) throws UnknownHostException {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                return InetAddress.getByName(this.userData.get(i).getAddress());
            }
        }
        return null;
    }

    public int getUserPort(String nome) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                return this.userData.get(i).getPortudp();
            }
        }
        return 0;
    }

    public PublicKey getUserPublicKey(String nome) {
        PublicKey p;
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                p = this.userData.get(i).publicKey;
                System.out.println(p);
                return p;

            }
        }
        return null;
    }

    public UserData getUserDataByName(String nome) {
        for (int i = 0; i < this.userData.size(); i++) {
            if (this.userData.get(i).getUserName().equals(nome)) {
                return this.userData.get(i);

            }
        }
        return null;
    }

    public PublicKey getUserByAddresPort(String address, int port) {
        PublicKey p = null;
        for (int i = 0; i < Peer2PeerProject.user.userData.size(); i++) {
            if (Peer2PeerProject.user.userData.get(i).getAddress().equals(address)) {
                if (Peer2PeerProject.user.userData.get(i).getPortudp() == port) {
                    p = Peer2PeerProject.user.userData.get(i).getPublicKey();
                    System.out.println("Usuario encontrado por address+port: " + Peer2PeerProject.user.userData.get(i).getUserName());
                    System.out.println("Chave Publica " + p);
                    return p;
                }
            }
        }
        System.out.println("Não encontrado usuario por address+port: ");
        return p;
    }

    public int getIndexUserPublicKey(byte[] message) {
        int i = 0;
        while (i < Peer2PeerProject.user.userData.size()) {
            try {
                if (!Criptografar.decriptografaPublica(message, Peer2PeerProject.user.userData.get(i).getPublicKey()).equals(null)) {
                    return i;
                    /*String mensagem = Criptografar.decriptografaPublica(aux, Peer2PeerProject.user.userData.get(i).getPublicKey());
                    System.out.println("Mensagem descriptografada: " + mensagem);*/
                }
            } catch (IllegalBlockSizeException ex) {
            } catch (BadPaddingException ex) {
            }
            i++;
        }
        return 0;
    }

    public boolean getboleanUserPublicKey(byte[] message) {
        int i = 0;
        while (i < Peer2PeerProject.user.userData.size()) {
            try {
                if (!Criptografar.decriptografaPublica(message, Peer2PeerProject.user.userData.get(i).getPublicKey()).equals(null)) {
                    return true;
                    /*String mensagem = Criptografar.decriptografaPublica(aux, Peer2PeerProject.user.userData.get(i).getPublicKey());
                    System.out.println("Mensagem descriptografada: " + mensagem);*/
                }
            } catch (IllegalBlockSizeException ex) {
            } catch (BadPaddingException ex) {
            }
            i++;
        }
        return false;
    }
}
