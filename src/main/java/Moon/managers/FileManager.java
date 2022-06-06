package Moon.managers;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private final static File dataFile = new File("data");

    private static String licenseKey = "enter-your-license-here";

    public static void CreateDataFile() {
        try {
            new File("data").mkdir();
        } catch (Exception ignored) {
        }
    }

    public static String loadPinCode() {
        try {
            final Scanner _scanner = new Scanner(new File("data", "saved.txt"));
            File folder = new File("data", "saved.txt");
            if (!folder.exists()) folder.mkdir();


            while (_scanner.hasNext()) {
                if (!licenseKey.isEmpty()) {
                    final String[] _split = _scanner.next().split(":", 2);
                    licenseKey = _split[0];
                }
            }
            _scanner.close();
        } catch (Exception ignored) {
        }
        return licenseKey;
    }

    public static void addCrash(String _methodName, String _packetName, Integer _pages, Integer _delay) {
        if (dataFile.exists()) {
            try (FileWriter _fileWriter = new FileWriter(dataFile + "/" + _methodName + ".txt")) {
                _fileWriter.write(_methodName + ":" + _packetName + ":" + _pages + ":" + _delay);
                _fileWriter.flush();
            } catch (IOException ignored) {
            }
        } else {
            CreateDataFile();
        }
    }
}