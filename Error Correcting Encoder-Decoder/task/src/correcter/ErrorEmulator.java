package correcter;

import java.util.*;
import java.util.stream.Collectors;

public class ErrorEmulator {
    static String createErrors(String input) {
        final String randomList = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final int N = randomList.length();

        char[] chars = input.toCharArray();
        Random random = new Random();
        int l = chars.length;
        for (int i = 0; i < l - 2; i += 3) {
            int j = random.nextInt(2);

            while (true) {
                char ch = randomList.charAt(random.nextInt(N));
                if (chars[i + j] != ch) {
                    chars[i + j] = ch;
                    break;
                }
            }

        }

        return new String(chars);
    }

    static ArrayList<String> createErrors(ArrayList<String> input) {
        Random random = new Random();
        ArrayList<String> output = new ArrayList<>();
        String[] strings = input.toArray(new String[0]);
        for (String s : strings) {
            char[] chars = s.toCharArray();

            int i = random.nextInt(7) + 1;
            while (true){
                char randomBit = (char) (random.nextInt(2) + 48);
                if (chars[i] != randomBit){
                    chars[i] = randomBit;
                    break;
                }
            }
            output.add(new String(chars));
        }

        return output;
    }
}