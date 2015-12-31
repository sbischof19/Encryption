package symmetric_ciphers;

import java.io.FileNotFoundException;



public class AES{
    private AESKeyGen keyGen;
    private String[][] keySBox;
    private String[] AESKeys;
    
    public AES() throws FileNotFoundException{
        keyGen = new AESKeyGen();
        keySBox = keyGen.getSBox();
        AESKeys = keyGen.getAESKeys();
    }
    
}