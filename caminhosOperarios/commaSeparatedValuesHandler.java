package caminhosOperarios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class commaSeparatedValuesHandler {
    public static int counterHeader = 0;

    public static List<String> readCsv(String filePath, Charset encoding) {
        BufferedReader reader = createBufferedReader(filePath, encoding);
        if (reader == null) {
            return new ArrayList<>();
        }
        return readLines(reader);
    }

    private static BufferedReader createBufferedReader(String filePath, Charset encoding) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding));
        } catch (Exception e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
            return null;
        }
    }

    private static List<String> readLines(BufferedReader reader) {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler as linhas do arquivo: " + e.getMessage());
        } finally {
            closeReader(reader);
        }
        return lines;
    }

    private static void closeReader(BufferedReader reader) {
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
        }
    }

}
