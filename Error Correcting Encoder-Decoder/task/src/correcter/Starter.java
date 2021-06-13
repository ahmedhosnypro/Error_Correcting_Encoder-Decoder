package correcter;
import java.util.Scanner;

import static correcter.Encoder.*;
import static correcter.Decoder.*;
import static correcter.Sender.send;

public class Starter {
    public static void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String input = scanner.nextLine().trim().toUpperCase();
        try {
            Mode mode = Mode.valueOf(input);
            System.out.println();
            switch (mode) {
                case SEND:
                    send();
                    break;
                case ENCODE:
                    encode();
                    break;
                case DECODE:
                    decode();
                    break;
                default:
                        break;
            }
        }
        catch (IllegalArgumentException e){
            System.out.print("check your input");
        }
    }

}
