package SweaterWeather;

import SweaterWeather.Model.LongLat;
import com.google.common.net.UrlEscapers;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class WeatherApi {


    private final HttpClient client = HttpClient.newHttpClient();

    private String getApiKey() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/key.txt"));
        String key = reader.readLine();
        return new String(Base64.getDecoder().decode(key));
    }

    public JsonObject getWeatherJsonByCityState(String city, String stateCode) throws URISyntaxException, IOException, InterruptedException {
        LongLat longLat = retrieveLongLat(city, stateCode);
        return retrieveWeatherByLongLat(longLat);
    }

    public LongLat retrieveLongLat(String city, String stateCode) throws URISyntaxException, IOException, InterruptedException {

        String defaultCountry = "USA";

        LongLat longLat = new LongLat();

        String cityStateCountry = city + "," + stateCode + "," + defaultCountry;
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + cityStateCountry + "&limit=1&appid=" + getApiKey();
        String encodedUrlString = UrlEscapers.urlFragmentEscaper().escape(url);

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(encodedUrlString)).GET().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        longLat.setLatitude(jsonObject.get("lat").getAsString());
        longLat.setLongitude(jsonObject.get("lon").getAsString());

        return longLat;
    }

    public JsonObject retrieveWeatherByLongLat(LongLat longLat) throws IOException, InterruptedException, URISyntaxException {
        String url = "http://api.openweathermap.org/data/2.5/forecast?lat=" + longLat.getLatitude() + "&lon=" + longLat.getLongitude() + "&appid=" + getApiKey() + "&units=imperial";
        String encodedUrlString = UrlEscapers.urlFragmentEscaper().escape(url);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(encodedUrlString)).GET().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }
}
