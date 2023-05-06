package file_manager;

import java.io.*;

public class FileManager {
    public File nistRunnerFile;

    public void runNIST() throws IOException{
        Runtime.getRuntime().exec("./" + nistRunnerFile.getName());
    }

    public void setupNistRunner() {
        String root = System.getProperty("user.dir");

        File nistrunnerfile = new File(root + "\\src\\nist\\run_nist.sh");
        try {
            if (!nistrunnerfile.exists())
                nistrunnerfile.createNewFile();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        nistRunnerFile = nistrunnerfile;
    }

    public void wipeNistRunner(){
        try {
            PrintWriter writer = new PrintWriter(nistRunnerFile);
            writer.print("");
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void appendNistRunner(String text){
        try {
            FileWriter writer = new FileWriter(nistRunnerFile, true);

            writer.write(text);
            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public File setupInput(String location){
        return new File(location);
    }

    public File setupOutput(String inputName, String outputLocation, String ending){
        try {
            String path = getFilePath(inputName, outputLocation, ending);
            File output = new File(path);
            System.out.println(path + " created!");

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

    public String getFilePath(String inputName, String outputLocation, String ending){
        String fileName = inputName;
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileName = fileName.substring(0, dotIndex);
        }
        return outputLocation + "\\" + fileName + ending;
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

    public void toBinaryFile(File textFile, String inputName, String outputLocation, String ending){
        File output = setupOutput(inputName, outputLocation, ending);

        try {
            FileInputStream fileInput = new FileInputStream(textFile);
            FileOutputStream byteWriter = new FileOutputStream(output);

            int[] oneByte = new int[8];

            int r;
            int bitCounter = 0;
            while ((r = fileInput.read()) != -1) {
                char c = (char) r;

                oneByte[bitCounter] = c-48;
                bitCounter++;

                if (bitCounter == 8){
                    //write one byte into toHash file
                    byte byteToWrite = (byte)funny_function(oneByte);
                    byteWriter.write(byteToWrite);

                    bitCounter = 0;
                }
            }
            fileInput.close();
            byteWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public char funny_function(int[] bit_array)
    {
        char result = 0;
        for(int i = 0; i < 8; i++)
        {
            result |= bit_array[i] << (7-i);
        }
        return result;
    }

    public char convertToHex(int[] bits) throws IllegalArgumentException {

        int decimal = 0;
        for (int i = 0; i < 4; i++) {
            decimal = decimal * 2 + bits[i];
        }

        return Integer.toHexString(decimal).charAt(0);
    }
}
