package caminhosOperarios;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

            String localGoogleMapsUrl = utils.getGoogleMapsURLWithLngLat(partes[0]);
            System.out.println(localGoogleMapsUrl);

            String routeGoogleMapsUrl = utils.generateGoogleMapsPolylineURL(utils.retrieveListOfCoordinates(partes[4]));
            System.out.println(routeGoogleMapsUrl);
        }

    }
}
