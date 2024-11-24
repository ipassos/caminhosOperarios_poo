package utils;

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

    // Method to parse WKT LineString and extract coordinates
    public static List<String[]> parseLineString(String wkt) {
        List<String[]> coordinates = new ArrayList<>();

        // Regular expression to match pairs of coordinates (longitude, latitude)
        String pattern = "-?\\d+\\.\\d+\\s-?\\d+\\.\\d+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(wkt);

        while (m.find()) {
            String[] coordinate = m.group().split(" ");
            coordinates.add(coordinate);
        }

        return coordinates;
    }

    public static String generateGoogleMapsPolylineURL(List<String[]> coordinates) {
        StringBuilder url = new StringBuilder("https://www.google.com/maps/dir/");

        // Build the polyline path for Google Maps
        for (String[] coord : coordinates) {
            url.append(coord[1]).append(",").append(coord[0]).append("/"); // Format: latitude,longitude
        }

        // Remove the last trailing slash
        url.deleteCharAt(url.length() - 1);

        return url.toString();
    }
}
