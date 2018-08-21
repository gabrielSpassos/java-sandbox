package br.com.gabrielspassos.poc;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class PublicKeyService {

    public RSAPublicKey createRSAPublicKey() throws Base64DecodingException, NoSuchAlgorithmException, InvalidKeySpecException {
        PbKey pbKey = new PbKey();
        byte[] decoded = Base64.decode(pbKey.getKey());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey generatePublic = (RSAPublicKey) kf.generatePublic(spec);
        BigInteger modulus = generatePublic.getModulus();
        BigInteger exponent = generatePublic.getPublicExponent();
        System.out.println("\n+++++++ RSA +++++++");
        System.out.println(modulus);
        System.out.println(exponent);
        return generatePublic;
    }

    public PublicKey createPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        PbKey pbKey = new PbKey();
        byte[] encodedPublicKey = com.sun.org.apache.xerces.internal.impl.dv.util.Base64.decode(pbKey.getKey());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedPublicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(spec);
        System.out.println("\n+++++++ Public Key +++++++");
        System.out.println(publicKey);
        return publicKey;
    }

}
