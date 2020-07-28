package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RewriteF {
    public static void rewriteFile(ObservableList<Assets> clist){
        // The same file is rewritten when you edit certain cell or delete a record
        // by passing the entire Assetlist
        try {
            FileWriter fw = new FileWriter("assets.csv");
            PrintWriter outfile = new PrintWriter(fw);
            for(Assets c: clist) {
                outfile.println(c.toString());
            }
            outfile.close();


        }catch (IOException e){
            e.printStackTrace();
        }

    }
}