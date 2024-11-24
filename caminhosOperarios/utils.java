package caminhosOperarios;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class utils {

    public static String generateGoogleMapsLocalURL(String wkt) {
        StringBuilder url = new StringBuilder("https://www.google.com/maps?q=");

        String[] valoresLatLng = utils.retrieveWKTLongLat(wkt);

        return(url.append(valoresLatLng[0]).append(",").append(valoresLatLng[1]).toString());
    }

    public static String generateGoogleMapsRouteURL(String coordinates) {
        StringBuilder url = new StringBuilder("https://www.google.com/maps/dir/");

        List<String[]> listCoordinates = retrieveListOfCoordinates(coordinates);

        for (String[] coord : listCoordinates) {
            url.append(coord[1]).append(",").append(coord[0]).append("/");
        }

        url.deleteCharAt(url.length() - 1);

        return url.toString();
    }
    public static String getWhatsInsideParenthesis(String dummyString) {
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(dummyString);
        String toReturnString= "";
        while (m.find()) {
            toReturnString = m.group(1);
        }

        return(toReturnString);
    }

    public static String[] retrieveWKTLongLat(String coordinates) {
        coordinates = getWhatsInsideParenthesis(coordinates);
        String[] latLng = coordinates.trim().split(" ");

        return new String[]{latLng[1].trim(),latLng[0].trim()};
    }

    public static List<String[]> retrieveListOfCoordinates(String coordinates) {
        String cleanCoordinates = getWhatsInsideParenthesis(coordinates);
        List<String[]> coordinatesToRoute = new ArrayList<>();

        for (String coord : List.of(cleanCoordinates.split(","))) {
            String[] latLng = coord.trim().split(" ");
            coordinatesToRoute.add(latLng);
        }

        return(coordinatesToRoute);
    }
}
