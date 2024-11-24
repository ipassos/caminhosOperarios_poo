package caminhosOperarios;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/*
0: wkt com o valor do ponto específico
1: nome da região
2: descrição
3: rota completa

Na query do google maps temos que passar lng + lat
*/

public class Main {
    public static void main(String[] args) {
        String filePath = "./dados/base_locais_trajetos.csv";
        Charset encoding = StandardCharsets.ISO_8859_1;

        List<String> lines = commaSeparatedValuesHandler.readCsv(filePath, encoding);
        String header = lines.getFirst();
        lines.removeFirst();

        for (String line : lines) {
            System.out.println(line);
            String[] partes = line.split(";");

            String localGoogleMapsUrl = utils.generateGoogleMapsLocalURL(partes[0]);
            System.out.println(localGoogleMapsUrl);

            String routeGoogleMapsUrl = utils.generateGoogleMapsRouteURL(partes[4]);
            System.out.println(routeGoogleMapsUrl);
        }

    }
}
