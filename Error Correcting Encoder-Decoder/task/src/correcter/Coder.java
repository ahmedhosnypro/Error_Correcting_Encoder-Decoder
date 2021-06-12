package correcter;

import java.util.ArrayList;

public class Coder {
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
}
