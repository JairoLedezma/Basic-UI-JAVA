package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveF {
    // A line is added  to the file whenever an object is added to the list
    // using the add button
    public static void saveFile(String content) throws IOException {
        FileWriter fw = new FileWriter("assets.csv", true);
        PrintWriter outfile = new PrintWriter(fw);
        outfile.println(content);
        outfile.close();
    }
}
