package symmetric_ciphers;

import java.io.FileNotFoundException;



public class AES{
    private AESKeyGen keyGeneration;
    private String[][] keySBox;
    private String[] AESKeys;
    
    public AES() throws FileNotFoundException{
        keyGeneration = new AESKeyGen();
        keySBox = keyGen.getSBox();
        AESKeys = keyGen.getAESKeys();
    }
    
}
