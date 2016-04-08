package peer2peerproject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Cauã
 */
public class Criptografar {

    public static final String ALGORITHM = "RSA";
    public static PrivateKey privateKey = null;
    public static PublicKey publicKey = null;
    public static Cipher cipher;

    public Criptografar() throws NoSuchAlgorithmException, NoSuchPaddingException {
        geraChave();
        cipher = Cipher.getInstance(ALGORITHM);
    }

    public static void geraChave() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        privateKey = key.getPrivate();
        publicKey = key.getPublic();        
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Criptografa o texto puro usando chave publica.
     */
    public static byte[] criptografaPublica(String texto, PublicKey chave) {
        byte[] cipherText = null;

        try {
            // Criptografa o texto puro usando a chave Publica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
        }

        return cipherText;
    }

    /**
     * Criptografa o texto puro usando chave privada.
     */
    public static byte[] criptografaPrivada(String texto, PrivateKey chave) {
        byte[] cipherText = null;

        try {
            // Criptografa o texto puro usando a chave Publica
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
        }

        return cipherText;
    }

    /**
     * Decriptografa o texto puro usando chave privada.
     */
    public static String decriptografaPrivada(byte[] texto, PrivateKey chave) {
        byte[] dectyptedText = null;

        try {
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
        }

        return new String(dectyptedText);
    }

    /**
     * Decriptografa o texto puro usando chave publica.
     */
    public static String decriptografaPublica(byte[] texto, PublicKey chave) throws IllegalBlockSizeException, BadPaddingException {
        byte[] dectyptedText = null;

        try {            
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (InvalidKeyException ex) {
            System.out.println(ex.getCause());
        }

        return new String(dectyptedText);
    }

    //CONVERTER STRINGS PARA KEY
    public static PublicKey stringToPublicKey(String pubkey) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decode(pubkey));
        PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);
        return publicKey2;

    }

    public static PrivateKey stringToPrivateKey(String privkey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privkey));
        PrivateKey privateKey2 = keyFactory.generatePrivate(privateKeySpec);
        return privateKey2;
    }

}
