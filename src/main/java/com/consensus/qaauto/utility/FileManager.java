package com.consensus.qaauto.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FileManager {
     public static void writeToFile(String fileName, String data){
        try {
            String filePath2 = Paths.get("test-output/report/Files/")+ "\\" + fileName;
            FileWriter fw2=new FileWriter(filePath2);
            fw2.write(data);
            fw2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
