package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        AESKeyGen keyGen = new AESKeyGen();
        String[] keys = keyGen.getAESKeys();
        for (int a = 0; a < keys.length; a++){
            System.out.println(a + " " + keys[a]);
        }


    }
}
