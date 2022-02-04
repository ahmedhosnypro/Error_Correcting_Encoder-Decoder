package correcter;

import java.io.File;
import java.util.Scanner;

import static correcter.Encoder.*;
import static correcter.Decoder.*;
import static correcter.Sender.send;

public class Starter {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a mode: ");
        String input = scanner.nextLine().trim().toUpperCase();

//        File sendFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/send.txt");
        File sendFile = new File("send.txt");
//        File encodedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
        File encodedFile = new File("encoded.txt");
//        File receivedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
        File receivedFile = new File("received.txt");
//        File decodedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/decoded.txt");
        File decodedFile = new File("decoded.txt");

        try {
            Mode mode = Mode.valueOf(input);
            System.out.println();
            switch (mode) {
                case ENCODE:
                    encodeToHamming(sendFile, encodedFile);
                    break;
                case SEND:
                    send(encodedFile, receivedFile);
                    break;
                case DECODE:
                    decodeHamming(receivedFile, decodedFile);
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.print("check your input");
        }
    }
}