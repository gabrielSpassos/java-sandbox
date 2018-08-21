package br.com.gabrielspassos.poc;

import org.junit.Assert;
import org.junit.Test;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import static org.junit.Assert.assertEquals;

public class PublicKeyServiceTest {

    PublicKeyService publicKeyService = new PublicKeyService();

    @Test
    public void shouldReturnTrue() {
        try {
            PublicKey publicKey = publicKeyService.createPublicKey();
            RSAPublicKey rsaPublicKey = publicKeyService.createRSAPublicKey();
            assertEquals(publicKey, rsaPublicKey);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
