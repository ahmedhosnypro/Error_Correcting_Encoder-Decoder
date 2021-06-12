package correcter;

import java.util.ArrayList;

public class Decoder {
    static String decode(String badString){
        char[] chars = badString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < chars.length - 1; i += 3){
            if (chars[i] == chars[i + 1]){
                sb.append(chars[i + 1]);
            }
            else if (chars[i] == chars[i - 1]){
                sb.append(chars[i - 1]);
            }
            else if (chars[i - 1] == chars[i + 1]){
                sb.append(chars[i - 1]);
            }

        }

        return sb.toString();
    }

    static byte[] bitsToBytes(ArrayList<String> input){
        byte[] bytes = new byte[input.size()];
        for (int i=0; i<input.size(); i++){
            String string = input.get(i);
            int l = Integer.parseInt(string);
            bytes[i] = Byte.parseByte(string, 2);
        }
        return bytes;
    }

    static String bytesToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes){
            sb.append(b).append(" ");
        }

        return sb.toString().trim();
    }
}
