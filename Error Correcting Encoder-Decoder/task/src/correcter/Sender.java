package correcter;

import java.io.*;
import java.util.ArrayList;

import static correcter.Encoder.*;
import static correcter.Decoder.*;
import static correcter.ErrorEmulator.*;

public class Sender {
    static void send(){
        File encodedFile = new File("encoded.txt");
//        File encodedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");
//        File receivedFile = new File("Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
        File receivedFile = new File("received.txt");
        try(FileInputStream encodedFileInputStream = new FileInputStream(encodedFile);
            FileOutputStream receivedFileOutputStream = new FileOutputStream(receivedFile);) {

            //read encodedFile
            //**************************//
            byte[] encodedBytes  = encodedFileInputStream.readAllBytes();
            //**************************//

            ArrayList<String> encodedHex = toHexArray(encodedBytes);
            ArrayList<String> encodedBits = toBitsArray(encodedBytes);

//            /*
            System.out.println(encodedFile.getName() + ":");
            System.out.print("hex view: ");
            encodedHex.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("bin view: ");
            encodedBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.println();
//             */

            //simulate error
            ArrayList<String> errorBits = createErrors(encodedBits);
            byte[] errorBytes = bitsToBytes(errorBits);
            ArrayList<String> errorHex = toHexArray(errorBytes);

//            /*
            System.out.println(receivedFile.getName() + ":");
            System.out.print("bin view: ");
            errorBits.forEach(s -> System.out.print(s + " "));
            System.out.println();
            System.out.print("hex view: ");
            errorHex.forEach(s -> System.out.print(s + " "));
//            */

            //send encoded file
            //**************************//
            receivedFileOutputStream.write(errorBytes);
            //*************************//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
