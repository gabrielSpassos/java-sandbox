package br.com.gabrielspassos.poc;

public class Main {
    public static void main(String[] args) {
        try{
            PublicKeyService publicKeyService = new PublicKeyService();
            publicKeyService.createPublicKey();
            publicKeyService.createRSAPublicKey();
        }catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
