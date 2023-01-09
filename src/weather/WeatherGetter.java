/*
* WEATHER GETTER! This is note is just for myself, if I leave it in it's by accident or it means
* that I didn't review my code.
* Don't focus too much on (java) classes other than this one because otherwise you'll go insane.
* Don't focus too much on getters & setters, only use what matters.
* Do the getters & setters as you go, but make sure to set every variable to private
* */

package weather;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.text.Normalizer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.image.Image;
import org.geojson.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.FeatureCollection;

public class WeatherGetter {
    // Files
    private static File locations = new File("locations.json");
    private static File userCity = new File("userCity.xml");
    private static File saveData = new File("save.txt");
    private static List<File> icons = new ArrayList<>();

    // Instances
    private static ObjectMapper mapper = new ObjectMapper();
    private static Scanner s = new Scanner(System.in);
    private static XmlMapper xmlMapper = new XmlMapper();
    private static FileWriter saveWriter;
    static {
        try {
            saveWriter = new FileWriter(saveData, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static PrintWriter savePrinter = new PrintWriter(saveWriter);

    // Variables
    private static FeatureCollection cities;
    private static List<Feature> searchResults;
    private static List<Feature> currItems;
    private static Feature selectedCity = null;
    private static int pageSelected = 0;
    public static SiteData city;

    // Getter for cities
    public static FeatureCollection getCities () {
        return cities;
    }

    // Getter for icon
    public static Image getIcon (int x) throws FileNotFoundException {
        return new Image(new FileInputStream(icons.get(x)));
    }

    // Self-explanatory, writes a file with a url to a file
    public static void writeURLToFile (URL url, File file) throws IOException {
        InputStream urlIS = url.openStream();
        OutputStream fileOS = new FileOutputStream(file);
        int c = urlIS.read();
        while (c != -1) {
            fileOS.write(c);
            c = urlIS.read();
        }
    }

    // Changes selected city
    public static void changeSelectedCity (Feature feature) {
        selectedCity = feature;
    }

    public static Feature getSelectedCity () {
        return selectedCity;
    }

    // Refreshes the data
    public static void refresh () throws IOException {
        System.out.println("Refreshing weather data for " + selectedCity.getProperties().get("English Names") + ", " +
                selectedCity.getProperties().get("Province Codes") + ".");
        writeURLToFile(returnXMLWeather(selectedCity), userCity); //Writes the user's city weather data to file
        city = xmlMapper.readValue(userCity, SiteData.class);
    }

    //Searches the locations.json file for a city. This is important because you can't just use a city's name in the API
    // Returns it in a list of features
    public static List<Feature> searchCities (String searchTerm, FeatureCollection cities) {
        List<Feature> searchResults;
        /* Case 1 : Searches for cities if it's not a code or a province name
         * Case 2 : Searches for a province and returns the cities if the name or code is correct */
        if (returnProvinceCode(searchTerm) == null) { // City
            String temp = searchTerm;
            if (searchTerm.toLowerCase().contains(" city")){ // So that you can search up quebec city. 1 hour of work just for that
                temp = searchTerm.toLowerCase().replace(" city", "");
            }
            String finalSearchTerm = temp;
            // returnPure() is important because of names like st. catharines, trois-rivières, and montréal
            searchResults = cities.getFeatures().stream()
                    .filter(feature -> returnPure(feature.getProperties().get("English Names").toString()).toLowerCase().contains(returnPure(finalSearchTerm.toLowerCase())))
                    .collect(Collectors.toList());
        } else {
            searchResults = cities.getFeatures().stream()
                    .filter(feature -> feature.getProperties().get("Province Codes").toString().equals(returnProvinceCode(searchTerm).toUpperCase()))
                    .collect(Collectors.toList());
        }
        return searchResults;
    }

    // @TODO make an updated search system that isn't just... That?

    // @todo sort cities alphabetically
    public static List<Feature> sortAlphabetically (List<Feature> list) {
        return list.stream().sorted(Comparator.comparing(feature -> feature.getProperties().get("English Names").toString())).collect(Collectors.toList());
    }

    // @todo put the ones where the search result matches first in front

    // sorts cities by distance
    public static List<Feature> sortByDistance (List<Feature> list) {
        return list.stream().sorted(Comparator.comparingDouble(feature -> sphericalDistance(
                        (double) selectedCity.getProperties().get("Latitude"),
                        (double) selectedCity.getProperties().get("Longitude"),
                        (double) feature.getProperties().get("Latitude"),
                        (double) feature.getProperties().get("Longitude"))))
                .collect(Collectors.toList());
    }

    // @todo limit cities based off of province

    // @todo limit cities based off of things

    // @todo haverson formula

    public static double sphericalDistance (double lat1, double lon1, double lat2, double lon2) {
        double distance = 6377.83027* Math.acos(Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lon2)
            - Math.toRadians(lon1)));
        return distance;
    }

    // REturns a list of features in a page (AKA pageN * pageSize to pageN*pageSize + pageSize)
    // Detects if a page is shorter and returns a shorter list if it does
    public static List<Feature> featurePagination (int pageN, List<Feature> list, int pageSize) {
        List<Feature> page = new ArrayList<>();
        for (int x = 0; x != pageSize; x++) {
            page.add(list.get(x + pageN * pageSize));
            if (x + pageN*pageSize == list.size() - 1) break;
        }
        return page;
    }

    // returnPure : Returns it in a "pure" form : aka without diacritics and punctuation.
    public static String returnPure (String str) {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        temp = temp.replaceAll("\\p{M}", "");
        temp = temp.replaceAll("[^a-zA-Z\\d\\s:]","");
        return temp;
    }

    // Used for the search feature, returns a code based off of a name. Also used for validating province codes
    public static String returnProvinceCode (String prov) {
        // This is here because the search function would process other two lettered words as codes if it weren't
        if (prov.length() == 2) {
            if (prov.equalsIgnoreCase("NL")) return "NL";
            if (prov.equalsIgnoreCase("PE")) return "PE";
            if (prov.equalsIgnoreCase("NS")) return "NS";
            if (prov.equalsIgnoreCase("NB")) return "NB";
            if (prov.equalsIgnoreCase("QC")) return "QC";
            if (prov.equalsIgnoreCase("ON")) return "ON";
            if (prov.equalsIgnoreCase("MB")) return "MB";
            if (prov.equalsIgnoreCase("SC")) return "SC";
            if (prov.equalsIgnoreCase("AB")) return "AB";
            if (prov.equalsIgnoreCase("BC")) return "BC";
            if (prov.equalsIgnoreCase("NT")) return "NT";
            if (prov.equalsIgnoreCase("YT")) return "YT";
            if (prov.equalsIgnoreCase("NU")) return "NU";
            return null;
        }
        if (prov.toLowerCase().contains("newfoundland") || prov.toLowerCase().contains("labrador")) return "NL";
        if (prov.equalsIgnoreCase("pei") || prov.equalsIgnoreCase("prince edward island")) return "PE";
        if (prov.equalsIgnoreCase("nova scotia")) return "NS";
        if (prov.equalsIgnoreCase("new brunswick")) return "NB";
        if (prov.equalsIgnoreCase("quebec")) return "QC";
        if (prov.equalsIgnoreCase("ontario")) return "ON";
        if (prov.equalsIgnoreCase("manitoba")) return "MB";
        if (prov.equalsIgnoreCase("saskatchewan")) return "SC";
        if (prov.equalsIgnoreCase("alberta")) return "AB";
        if (prov.equalsIgnoreCase("british columbia")) return "BC";
        if (prov.equalsIgnoreCase("nw territories") || prov.equalsIgnoreCase("north-west territories")
                || prov.equalsIgnoreCase("northwest territories")) return "NT";
        if (prov.equalsIgnoreCase("yukon") || prov.equalsIgnoreCase("yukon territory")) return "YT";
        if (prov.equalsIgnoreCase("nunavut")) return "NU";
        return null;
    }

    // Returns the URL for the xml data of a city, using MSC Datamart
    public static URL returnXMLWeather (Feature city) throws MalformedURLException {
        String temp = "https://dd.weather.gc.ca/citypage_weather/xml/" + city.getProperties().get("Province Codes")
                + "/" + city.getProperties().get("Codes") + "_e.xml";
        return new URL(temp);
    }

    // Creates new file etc but V2! For use with the visual

    public static void visualInitialize () throws IOException {
        // Creates these files if not created
        if (saveData.createNewFile()) {
            System.out.println("Save data created.");
        }
        if (locations.createNewFile()) {
            System.out.println("Location data created.");
            writeURLToFile(new URL("https://collaboration.cmc.ec.gc.ca/cmc/cmos/public_doc/msc-data/citypage-weather/site_list_en.geojson"), locations);
        }
        // Locations JSON to the object cities... I don't know why oracle hasn't put out an official library for this
        cities = mapper.readValue(locations, FeatureCollection.class);
        if (new File("src/icons").mkdir()) {
            System.out.println("Icons downloaded");
        }
        for (int x = 0; x != 49; x++) {
                icons.add(new File("src/icons/" + String.format("%02d", x ) + ".gif"));
                if (icons.get(x).createNewFile()) {
                    try {
                        writeURLToFile(new URL("https://meteo.gc.ca/weathericons/" + String.format("%02d", x) + ".gif"), icons.get(x));
                    } catch (IOException ignored) {

                    }
                }
        }
    }

    // Creates new file, maps the city JSON to cities, 
    public static void initialize () throws IOException {
        // Creates these files if not created
        if (saveData.createNewFile()) {
            System.out.println("Save data created.");
        }
        if (locations.createNewFile()) {
            System.out.println("Location data created.");
            writeURLToFile(new URL("https://collaboration.cmc.ec.gc.ca/cmc/cmos/public_doc/msc-data/citypage-weather/site_list_en.geojson"), locations);
        }

        // Locations JSON to the object cities... I don't know why oracle hasn't put out an official library for this
        cities = mapper.readValue(locations, FeatureCollection.class);


        // Structure for searches
        do {
            // @Todo modify this to work with a visual interface
            System.out.println("Please search the city / province (2 letter code) that you would like to see: ");
            searchResults = searchCities(s.nextLine(), cities);
            while (searchResults.size() < 1) {
                System.out.println("No results; search a different term.");
                searchResults = searchCities(s.nextLine(), cities);
            }

            // @Todo modify this to work with selection
            boolean condition = false;
            while (!condition) {
                currItems = featurePagination(pageSelected, searchResults, 4);
                for (int x = 0; x != currItems.size(); x++) {
                    System.out.println("[" + (x+1) + "] . " + currItems.get(x).getProperties().get("English Names"));
                }
                System.out.println("[X] search again");
                System.out.println("< or > to change pages (Currently on page " + (pageSelected+1) + ")");
                System.out.println("Your input: ");
                String input = s.nextLine();

                try { // Tries to parse an input
                    if (0 < Integer.parseInt(input) || Integer.parseInt(input) > currItems.size()+1) {
                        selectedCity = currItems.get(Integer.parseInt(input) - 1);
                        condition = true;
                    } else Integer.parseInt("a");
                } catch (NumberFormatException e) { // Catches it if it's not an integer
                    if (input.equalsIgnoreCase("x")) {
                        selectedCity = null;
                        condition = true;
                        pageSelected = 0;
                    } else if (input.equalsIgnoreCase("<")) {
                        if (pageSelected != 0) {
                            pageSelected --;
                        } else System.out.println("Failure");
                    } else if (input.equalsIgnoreCase(">")) {
                        if (pageSelected != searchResults.size() / 4) {
                            pageSelected ++;
                        } else System.out.println("Failure");
                    }
                } catch (IndexOutOfBoundsException e) { // If it's out of bounds
                    System.out.println("Out of bounds, please choose something else.");
                }
            }
        } while (selectedCity == null);

        savePrinter.print(selectedCity.getProperties().get("English Names") + "\n");
        savePrinter.close();

        System.out.println("Getting weather data for " + selectedCity.getProperties().get("English Names") + ", " +
                selectedCity.getProperties().get("Province Codes") + ".");
        writeURLToFile(returnXMLWeather(selectedCity), userCity); //Writes the user's city weather data to file
        city = xmlMapper.readValue(userCity, SiteData.class);
        // Test values
    }

    public static void main (String[] args) throws IOException {
        // Test
        initialize();
        System.out.println(sphericalDistance(43.6532, -79.3832, 45.4215, -75.6972) + " KM");
        System.out.println(selectedCity.getProperties());
        System.out.println();
        List<Feature> sortedByDistance = sortByDistance(searchResults);
        for (Feature f : sortedByDistance) {
            System.out.println(f.getProperties().get("English Names"));
        }
        System.out.println();
        List<Feature> sortedAlphabet = sortAlphabetically(searchResults);
        for (Feature f : sortedAlphabet) {
            System.out.println(f.getProperties().get("English Names"));
        }
    }
}