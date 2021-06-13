package correcter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static correcter.Decoder.bitsToBytes;
import static correcter.Decoder.bytesToString;

public class Encoder {
    public static void encode(){
//        File sendFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/send.txt");
        File sendFile = new File("send.txt");
//        File encodedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
        File encodedFile = new File("encoded.txt");
        try(FileInputStream sendFileInputStream = new FileInputStream(sendFile);
            FileOutputStream encodedFileOutputStream = new FileOutputStream(encodedFile);){

            //read send file
            byte[] bytes  = sendFileInputStream.readAllBytes();
            ArrayList<String> hex = toHexArray(bytes);
            ArrayList<String> bin = toBitsArray(bytes);

//            /*
            System.out.println(sendFile.getName() + ":");
            System.out.println("text view: " + bytesToString(bytes));
            System.out.print("hex view: ");
            hex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("bin view: ");
            bin.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println();
//             */

            //encode send file
            ArrayList<String> encodedBin = encode(toBitsArray(bytes));
            ArrayList<String> encodedHex = toHexArray(bitsToBytes(encodedBin));

//            /*
            System.out.println(encodedFile.getName() + ":");
            System.out.print("parity: ");
            encodedBin.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("hex view: ");
            encodedHex.forEach(s -> System.out.print(s + " "));
//             */

            //**************************//
            encodedFileOutputStream.write(bitsToBytes(encodedBin));
            //**************************//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> encode(ArrayList<String> bits){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> output = new ArrayList<String>();
        for (String s: bits){
            sb.append(s);
        }

        int length = sb.length();
        int bound = length % 3;

        for (int i=0; i<sb.length()-bound; i +=3){
            StringBuilder sb1 = new StringBuilder();

            char ch1 = sb.charAt(i);
            char ch2 = sb.charAt(i+1);
            char ch3 = sb.charAt(i+2);

            sb1.append(String.valueOf(ch1).repeat(2));
            sb1.append(String.valueOf(ch2).repeat(2));
            sb1.append(String.valueOf(ch3).repeat(2));


            int n1  = ch1 - 48;
            int n2  = ch2 - 48;
            int n3  = ch3 - 48;
            int n4 = n1 ^ n2 ^ n3;

            sb1.append(String.valueOf(n4).repeat(2));
            output.add(sb1.toString());
        }

        if (bound == 1){
            StringBuilder sb1 = new StringBuilder();
            char ch = sb.charAt(sb.length() - bound);
            int n1 = ch - 48;
            int n2 = 0, n3 = 0;
            int m = n1 ^ n2 ^ n3;
            sb1.append(String.valueOf(ch).repeat(2));
            sb1.append(String.valueOf(0).repeat(4));
            sb1.append(String.valueOf(m).repeat(2));
            output.add(sb1.toString());
        }

        else if (bound == 2){
            StringBuilder sb1 = new StringBuilder();
            char ch1 = sb.charAt(sb.length() - 2);
            char ch2 = sb.charAt(sb.length() - 1);
            int n1 = ch1 - 48;
            int n2 = ch2 - 48;
            int n3 = 0;
            int m = n1 ^ n2 ^ n3;
            sb1.append(String.valueOf(ch1).repeat(2));
            sb1.append(String.valueOf(ch2).repeat(2));
            sb1.append(String.valueOf(0).repeat(2));
            sb1.append(String.valueOf(m).repeat(2));

            output.add(sb1.toString());
        }

        return output;
    }

    public static ArrayList<String> toBitsArray(byte[] bytes){
        ArrayList<String> list = new ArrayList<String>();
        for (byte b: bytes){
            list.add(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return list;
    }

    public static ArrayList<String> toHexArray(byte[] bytes) {
        ArrayList<String> list = new ArrayList<String>();
        for (byte b: bytes){
            list.add(String.format("%02X", b));
        }

        return list;
    }

    static String triple(String input){
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch: chars){
            for (int i = 0; i < 3; i++){
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
