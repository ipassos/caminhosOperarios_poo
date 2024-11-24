package caminhosOperarios;

import utils.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "./dados/total_base_locais_trajetos.csv";
        Charset encoding = StandardCharsets.ISO_8859_1;

        List<String> lines = commaSeparatedValuesHandler.readCsv(filePath, encoding);
        String header = lines.getFirst();
        lines.removeFirst();

        for (String line : lines) {
            String[] partes = line.split(";");
            String[] valoresLatLng = utils.retrieveWKTLongLat(partes[0]);
            String lng = valoresLatLng[0];
            String lat = valoresLatLng[1];

            String googleMapsURL = "https://www.google.com/maps?q=" + lng + "," + lat;
            System.out.println(googleMapsURL);

            List<String[]> coords = utils.parseLineString(partes[4]);
            System.out.println(utils.generateGoogleMapsPolylineURL(coords));

            }
        }
}
