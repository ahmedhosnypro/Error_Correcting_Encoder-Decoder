package correcter;

import java.util.Random;
import java.util.Scanner;

import static correcter.Coder.triple;
import static correcter.Decoder.decode;
import static correcter.ErrorEmulator.createErrors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        String triple = triple(input);
        String errors = createErrors(triple);
        String decoded = decode(errors);
        System.out.println(input);
        System.out.println(triple);
        System.out.println(errors);
        System.out.println(decoded);

    }
}
