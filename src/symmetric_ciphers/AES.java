package com.company;

/**
 * Created by scott_000 on 1/8/2016.
 */
import java.io.FileNotFoundException;

public class AES extends CipherMethods{
    private AESKeyGen keyGen;
    private String[][] keySBox;
    private String[] AESKeys;
    private String[] currentKeyArray;
    private String[] messageArray;
    private String currentKey;

    public AES() throws FileNotFoundException{
        keyGen = new AESKeyGen();
        keySBox = keyGen.getSBox();
        AESKeys = keyGen.getAESKeys();
        currentKeyArray = new String[AESKeys.length];
        currentKey = keyGen.getCipherKey();
        for (int a = 0; a < 16; a++){
            currentKeyArray[a]="0";
            messageArray[a]="0";
        }
    }

    public String convertToHex(String message){
        message = asciiConvert(message,16);
        return message;
    }

    public String runCipher(String message){
        String result = "";
        createMessageArray(message);
        addRoundKey();
        //loop
        subBytes();
        shiftRows();
        //end loop



        return result;
    }

    private void createMessageArray(String message){

        for (int a = 0; a < message.length(); a=a+2){
            messageArray[a/2] = message.substring(a, a+2);
        }
    }

    private void addRoundKey(){

        for (int a = 0; a < currentKeyArray.length; a=a+2){
            currentKeyArray[a/2] = currentKey.substring(a, a+2);
        }

        for (int b = 0; b < currentKeyArray.length; b++){
            messageArray[b] = Integer.toHexString((Integer.parseInt(messageArray[b],16))^(Integer.parseInt(currentKeyArray[b],16)));
        }

    }

    private void subBytes() {
        String result = "";
        int temp;
        int X = 0;
        int Y = 0;

        for (int a = 0; a < messageArray.length; a++) {

            if (Integer.parseInt(messageArray[a].substring(0, 1), 16) < 10) {
                X = Integer.parseInt(messageArray[a].substring(0, 1));
            }
            else
            {
                switch (messageArray[a].substring(0, 1)) {
                    case "a":
                        X = 10;
                        break;
                    case "b":
                        X = 11;
                        break;
                    case "c":
                        X = 12;
                        break;
                    case "d":
                        X = 13;
                        break;
                    case "e":
                        X = 14;
                        break;
                    case "f":
                        X = 15;
                        break;
                }
            }

            if (Integer.parseInt(messageArray[a].substring(1, 2), 16) < 10) {
                Y = Integer.parseInt(messageArray[a].substring(1, 2));
            }
            else
            {
                switch (messageArray[a].substring(1, 2)) {
                    case "a":
                        Y = 10;
                        break;
                    case "b":
                        Y = 11;
                        break;
                    case "c":
                        Y = 12;
                        break;
                    case "d":
                        Y = 13;
                        break;
                    case "e":
                        Y = 14;
                        break;
                    case "f":
                        Y = 15;
                        break;
                }
            }
            messageArray[a] = keySBox[X][Y];

        }

    }

    private void shiftRows(){

        String[] temp = new String[messageArray.length];
        int b = 0;
        int c = 0;
        for (int a = 0; a < messageArray.length; a=a+4){
            b = a/4;
            c = a-b;
            if (c < a){
                c = c + 4;
            }
            temp[c] = messageArray[a];

            c = (a+1)-b;
            if (c < a){
                c = c + 4;
            }
            temp[c] = messageArray[a+1];

            c = (a+2)-b;
            if (c < a){
                c = c + 4;
            }
            temp[c] = messageArray[a+2];

            c = (a+3)-b;
            if (c < a){
                c = c + 4;
            }
            temp[c] = messageArray[a+3];

        }
        messageArray = temp;
    }


}