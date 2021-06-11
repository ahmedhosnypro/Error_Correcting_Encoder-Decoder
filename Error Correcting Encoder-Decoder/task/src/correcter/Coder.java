package correcter;

import java.util.Arrays;

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
}
