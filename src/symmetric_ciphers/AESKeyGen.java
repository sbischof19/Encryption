package symmetric_ciphers;

import java.io.FileNotFoundException;

public class AESKeyGen extends CipherMethods {

    private String[][] keySBox;
    private String[] keyArray;
    private String[] subBytesArray;
    private String[] AESKeys;
    private String cipherHex;

    public AESKeyGen() throws FileNotFoundException {
        
        keySBox = new String[16][16];
        keyArray = new String[cipherHex.length() / 2];
        subBytesArray = new String[cipherHex.length() / 2];
        AESKeys = new String[10];
        cipherHex = "4142434445464748494a4b4c4d4e4f50";
                
        int b = 0;
        for (int a = 0; a < keyArray.length; a++) {
            keyArray[a] = cipherHex.substring(b, b + 2);
            subBytesArray[a] = keyArray[a];
            b = b + 2;

        }

        createSBox();

        for (int c = 0; c < AESKeys.length; c++) {
            reOrder();
            getKeys();
            AESKeys[c] = rCon(c);
        }

    }

    public String[] getAESKeys() {
        return AESKeys;
    }

    public String[][] getSBox() {
        return keySBox;
    }

    private void createSBox() {
        String temp = "";
        int num = 0;
        int num2 = 0;
        for (int a = 0; a < 16; a++) {

            for (int b = 0; b < 16; b++) {
                num = (int) (Math.random() * 16);

                if (num > 9) {
                    switch (num) {
                        case 10:
                            temp += "a";
                            break;
                        case 11:
                            temp += "b";
                            break;
                        case 12:
                            temp += "c";
                            break;
                        case 13:
                            temp += "d";
                            break;
                        case 14:
                            temp += "e";
                            break;
                        case 15:
                            temp += "f";
                            break;
                    }
                } else {
                    temp += num;
                }

                num2 = (int) (Math.random() * 16);
                if (num2 > 9) {
                    switch (num2) {
                        case 10:
                            temp += "a";
                            break;
                        case 11:
                            temp += "b";
                            break;
                        case 12:
                            temp += "c";
                            break;
                        case 13:
                            temp += "d";
                            break;
                        case 14:
                            temp += "e";
                            break;
                        case 15:
                            temp += "f";
                            break;
                    }
                } else {
                    temp += num2;
                }

                keySBox[a][b] = temp;
                temp = "";
                
            }
            
        }

    }

    private void reOrder() {

        String[] temp = new String[4];

        for (int a = 0; a < keyArray.length; a++) {

            temp[a % 4] = keyArray[a];

            if ((a + 1) % 4 == 0) {

                subBytesArray[a] = temp[0];

                subBytesArray[a - 3] = temp[1];

                subBytesArray[a - 2] = temp[2];

                subBytesArray[a - 1] = temp[3];

            }

        }

    }

    private void getKeys() {
        String result = "";
        int temp;
        int X = 0;
        int Y = 0;

        for (int a = 0; a < subBytesArray.length; a++) {

            if (Integer.parseInt(subBytesArray[a].substring(0, 1), 16) < 10) {
                X = Integer.parseInt(subBytesArray[a].substring(0, 1));
            } 
            else 
            {
                switch (subBytesArray[a].substring(0, 1)) {
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

            if (Integer.parseInt(subBytesArray[a].substring(1, 2), 16) < 10) {
                Y = Integer.parseInt(subBytesArray[a].substring(1, 2));
            } 
            else 
            {
                switch (subBytesArray[a].substring(1, 2)) {
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
            subBytesArray[a] = keySBox[X][Y];

        }

    }

    private String rCon(int a) {
        String result = "";
        String[] rcon = new String[4];
        switch (a) {
            case 0:
                rcon[0] = "01";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";
                break;
            case 1:
                rcon[0] = "02";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 2:
                rcon[0] = "04";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 3:
                rcon[0] = "08";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 4:
                rcon[0] = "10";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 5:
                rcon[0] = "20";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 6:
                rcon[0] = "40";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 7:
                rcon[0] = "80";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 8:
                rcon[0] = "1b";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
            case 9:
                rcon[0] = "36";
                rcon[1] = "00";
                rcon[2] = "00";
                rcon[3] = "00";

                break;
        }

        int c = subBytesArray.length - 1;
        for (int b = 0; b < keyArray.length; b++) {

            result += String.format("%2s", Integer.toHexString((Integer.parseInt(keyArray[b], 16) ^ Integer.parseInt(subBytesArray[c], 16)) ^ Integer.parseInt(rcon[b % 4], 16))).replace(' ', '0');
            keyArray[b] = String.format("%2s", Integer.toHexString((Integer.parseInt(keyArray[b], 16) ^ Integer.parseInt(subBytesArray[c], 16)) ^ Integer.parseInt(rcon[b % 4], 16))).replace(' ', '0');
            c--;
        }

        return result;
    }

}
