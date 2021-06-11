package correcter;

import java.util.Random;
import java.util.Scanner;

import static correcter.ErrorEmulator.createErrors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(createErrors(scanner.nextLine().trim()));

    }
}
