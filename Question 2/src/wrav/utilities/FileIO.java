package wrav.utilities;

import wrav.datastructures.WRAVQueue;
import java.io.PrintWriter;
import java.io.FileWriter;

public class FileIO {
    public static void writeToFile(WRAVQueue theQueue, String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println(theQueue);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
