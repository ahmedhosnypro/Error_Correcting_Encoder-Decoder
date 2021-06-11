package correcter;

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
}
