package correcter;

import java.util.Random;
import java.util.Scanner;

public class ErrorEmulator {
    static String createErrors(String input){
        final String randomList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final int N = randomList.length();

        char[] chars = input.toCharArray();
        Random random = new Random();
        int l = chars.length;
        for (int i = 0; i < l - 2; i += 3){
            int j = random.nextInt(2);

            while (true){
                char ch = randomList.charAt(random.nextInt(N));
                if (chars[i + j] != ch){
                    chars[i + j] = ch;
                    break;
                }
            }

        }

        return new String(chars);
    }

}
