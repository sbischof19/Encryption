package com.company;

/**
 * Created by scott_000 on 1/8/2016.
 */
import java.io.FileNotFoundException;

public class AESKeyGen extends CipherMethods {

    private String[][] keySBox;
    private String[] keyArray;
    private String[] subBytesArray;
    private String[] AESKeys;
    private String cipherKey;

    public AESKeyGen() throws FileNotFoundException {


        cipherKey = "37b3979d4a7c5b6c00f82bb3b176e002";
        keySBox = new String[][]{
                {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
                {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
        };
        
        keyArray = new String[cipherKey.length() / 2];
        subBytesArray = new String[cipherKey.length() / 2];
        AESKeys = new String[10];


        int b = 0;
        for (int a = 0; a < keyArray.length; a++) {
            keyArray[a] = cipherKey.substring(b, b + 2);
            subBytesArray[a] = keyArray[a];
            b = b + 2;

        }

       // createSBox();
        
        

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

    public String getCipherKey() {
        return cipherKey;
    }

   /*private void createSBox() {
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

    }*/

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