package caminhosOperarios;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "./dados/base_locais_trajetos.csv";
        Charset encoding = StandardCharsets.ISO_8859_1;

        List<String> lines = commaSeparatedValuesHandler.readCsv(filePath, encoding);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
