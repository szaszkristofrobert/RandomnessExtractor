package file_manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {
    public File setupInput(String location){
        return new File(location);
    }

    public File setupOutput(String inputName, String outputLocation, String ending){
        try {
            String fileName = inputName;
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                fileName = fileName.substring(0, dotIndex);
            }
            String path = outputLocation + "\\" + fileName + ending;
            System.out.println(path + " created!");
            File output = new File(path);

            if (!output.exists()) {
                output.createNewFile();
            }

            return output;
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public void copyContent(File a, File b)
            throws IOException
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);

        try {

            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }
}
