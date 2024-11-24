package caminhosOperarios;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {

    public static String[] retrieveWKTLongLat(String wkt) {
        wkt = wkt.replace("(", "").replace(")", "");
        String[] partes = wkt.split(" ");
        String lat = partes[1];
        String lng = partes[2];

        System.out.println(lat);
        System.out.println(lng);

        return new String[]{lng.trim(),lat.trim()};
    }

    public static String getGoogleMapsURLWithLngLat(String wkt) {
        StringBuilder url = new StringBuilder("https://www.google.com/maps?q=");

        String[] valoresLatLng = utils.retrieveWKTLongLat(wkt);

        return(url.append(valoresLatLng[0]).append(",").append(valoresLatLng[1]).toString());
    }

    public static String generateGoogleMapsPolylineURL(List<String[]> coordinates) {
        StringBuilder url = new StringBuilder("https://www.google.com/maps/dir/");

        for (String[] coord : coordinates) {
            url.append(coord[1]).append(",").append(coord[0]).append("/");
        }

        url.deleteCharAt(url.length() - 1);

        return url.toString();
    }

    public static List<String[]> retrieveListOfCoordinates(String coordinates) {

        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(coordinates);
        String StrCoordenadas = "";
        while (m.find()) {
            StrCoordenadas = m.group(1);
        }
        List<String[]> coordinatesToRoute = new ArrayList<>();

        for (String coord : List.of(StrCoordenadas.split(","))) {
            String[] latLng = coord.trim().split(" ");
            coordinatesToRoute.add(latLng);
        }

        return(coordinatesToRoute);
    }
}
