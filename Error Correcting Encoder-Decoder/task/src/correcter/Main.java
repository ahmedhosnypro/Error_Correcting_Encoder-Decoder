package correcter;

import java.io.*;
import java.util.Arrays;

import static correcter.Coder.*;
import static correcter.Decoder.*;
import static correcter.ErrorEmulator.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        "Error Correcting Encoder-Decoder/task/src/correcter/send.txt"
        File sendFile = new File("send.txt");
//        "Error Correcting Encoder-Decoder/task/src/correcter/received.txt"
        File receivedFile = new File("received.txt");
        try(FileInputStream fileInputStream = new FileInputStream(sendFile);
            FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
            ) {
            byte[] bytes  = fileInputStream.readAllBytes();

            fileOutputStream.write(bitsToBytes(createErrors(toBitsArray(bytes))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


//            System.out.println(toBitsArray(bytes));
//            System.out.println(createErrors(toBitsArray(bytes)));
//            System.out.println(toHexArray(bytes));
//            System.out.println(Arrays.toString(bitsToBytes(createErrors(toBitsArray(bytes)))));
//            System.out.println(Arrays.toString(bytes));
//            System.out.println(bytesToString(bitsToBytes(createErrors(toBitsArray(bytes)))));

//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine().trim();
//        String triple = triple(input);
//        String errors = createErrors(triple);
//        String decoded = decode(errors);
//        System.out.println(input);
//        System.out.println(triple);
//        System.out.println(errors);
//        System.out.println(decoded);