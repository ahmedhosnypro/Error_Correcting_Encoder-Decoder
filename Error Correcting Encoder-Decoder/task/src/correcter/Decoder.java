package correcter;

import java.io.*;
import java.util.ArrayList;

import static correcter.Encoder.*;

public class Decoder {

    //edit this
    public static void decodeHamming(File receivedFile, File decodedFile) {
        try (FileInputStream receivedFileInputStream = new FileInputStream(receivedFile);
             FileOutputStream decodedFileOutputStream = new FileOutputStream(decodedFile)) {

            //read encodedFile
            //**************************//
            byte[] receivedBytes = receivedFileInputStream.readAllBytes();
            //**************************//

            ArrayList<String> receivedHex = toHexArray(receivedBytes);
            ArrayList<String> receivedBits = toBitsArray(receivedBytes);

            ///*
            System.out.println(receivedFile.getName() + ":");
            System.out.print("hex view: ");
            receivedHex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("bin view: ");
            receivedBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println();
            //*/

            //decode received file
            ArrayList<String> decodedBits = decodeHamming(receivedBits);
            byte[] decodedBytes = bitsToBytes(decodedBits);
            ArrayList<String> decodedHex = toHexArray(decodedBytes);

            ///*
            System.out.println(decodedFile.getName() + ":");
            System.out.print("correct: ");
            decodedBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("hex view: ");
            decodedHex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println("text view: " + bytesToString(decodedBytes));
            //*/

            //**************************//
            decodedFileOutputStream.write(bitsToBytes(decodedBits));
            //**************************//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> decodeHamming(ArrayList<String> bits) {
        ArrayList<String> output = new ArrayList<>();
        StringBuilder out = new StringBuilder();

        for (String B : bits) {
            int p1 = B.charAt(0) - 48;
            int p2 = B.charAt(1) - 48;
            int d3 = B.charAt(2) - 48;
            int p4 = B.charAt(3) - 48;
            int d5 = B.charAt(4) - 48;
            int d6 = B.charAt(5) - 48;
            int d7 = B.charAt(6) - 48;

            int errorPosition = 0;
            if ((d3 ^ d5 ^ d7) != p1) {
                errorPosition++;
            }
            if ((d3 ^ d6 ^ d7) != p2) {
                errorPosition += 2;
            }
            if ((d5 ^ d6 ^ d7) != p4) {
                errorPosition += 4;
            }
            switch (errorPosition) {
                case 3:
                    d3 ^= 1;
                    break;
                case 5:
                    d5 ^= 1;
                    break;
                case 6:
                    d6 ^= 1;
                    break;
                case 7:
                    d7 ^= 1;
                    break;
                default:
                    break;
            }
            if (out.length() == 8) {
                output.add(out.toString());
                out = new StringBuilder();
            }
            out.append(d3).append(d5).append(d6).append(d7);
        }

        if (out.length() == 8) {
            output.add(out.toString());
        }
        return output;
    }

    public static void decode(File receivedFile, File decodedFile) {
        try (FileInputStream receivedFileInputStream = new FileInputStream(receivedFile);
             FileOutputStream decodedFileOutputStream = new FileOutputStream(decodedFile)) {

            //read encodedFile
            //**************************//
            byte[] receivedBytes = receivedFileInputStream.readAllBytes();
            //**************************//

            ArrayList<String> receivedHex = toHexArray(receivedBytes);
            ArrayList<String> receivedBits = toBitsArray(receivedBytes);

            ///*
            System.out.println(receivedFile.getName() + ":");
            System.out.print("hex view: ");
            receivedHex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("bin view: ");
            receivedBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println();
            //*/

            //decode received file
            ArrayList<String> decodedBits = decode(receivedBits);
            ArrayList<String> decodedHex = toHexArray(receivedBytes);
            byte[] decodedBytes = bitsToBytes(decodedBits);

            ///*
            System.out.println(decodedFile.getName() + ":");
            System.out.print("correct: ");
            decodedBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("hex view: ");
            decodedHex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println("text view: " + bytesToString(decodedBytes));
            //*/

            //**************************//
            decodedFileOutputStream.write(bitsToBytes(decodedBits));
            //**************************//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> decode(ArrayList<String> bits) {
        ArrayList<String> output = new ArrayList<>();

        StringBuilder out = new StringBuilder();

        for (String B : bits) {
            for (int i = 0; i < B.length() - 2; i += 2) {
                if (B.charAt(i) != B.charAt(i + 1)) {
                    int n1 = B.charAt(0) - 48;
                    int n2 = B.charAt(2) - 48;
                    int n3 = B.charAt(4) - 48;
                    int n4 = B.charAt(6) - 48;
                    int m = 0;
                    switch (i) {
                        case 0:
                            m = n2 ^ n3 ^ n4;
                            break;
                        case 2:
                            m = n1 ^ n3 ^ n4;
                            break;
                        case 4:
                            m = n1 ^ n2 ^ n4;
                            break;
                        default:
                            break;
                    }
                    if (out.length() == 8) {
                        output.add(out.toString());
                        out = new StringBuilder();
                    }
                    out.append(m);
                } else {
                    if (out.length() == 8) {
                        output.add(out.toString());
                        out = new StringBuilder();
                    }
                    out.append(B.charAt(i));
                }
            }
        }

        if (out.length() == 8) {
            output.add(out.toString());
        }
        return output;
    }

    static byte[] bitsToBytes(ArrayList<String> input) {

        byte[] bytes = new byte[input.size()];
        for (int i = 0; i < input.size(); i++) {
            String string = input.get(i);
            int n = 0;
            for (int j = 7; j >= 0; j--) {
                n += (int) ((string.charAt(Math.abs(7 - j)) - 48) * Math.pow(2, j));
            }
            if (n > 127)
                n -= 256;
            bytes[i] = (byte) n;
        }

        return bytes;
    }

    static String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            char ch = (char) b;
            sb.append(ch);
        }

        return sb.toString().trim();
    }

    static String decode(String badString) {
        char[] chars = badString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length - 1; i += 3) {
            if (chars[i] == chars[i + 1]) {
                sb.append(chars[i + 1]);
            } else if (chars[i] == chars[i - 1]) {
                sb.append(chars[i - 1]);
            } else if (chars[i - 1] == chars[i + 1]) {
                sb.append(chars[i - 1]);
            }

        }

        return sb.toString();
    }
}