package SweaterWeather;

import SweaterWeather.Model.LongLat;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApi {


    private final HttpClient client = HttpClient.newHttpClient();

    public JsonObject getWeatherJsonByCityState(String city, String stateCode) throws URISyntaxException, IOException, InterruptedException {
        LongLat longLat = retrieveLongLat(city, stateCode);
        return retrieveWeatherByLongLat(longLat);
    }

    public LongLat retrieveLongLat(String city, String stateCode) throws URISyntaxException, IOException, InterruptedException {

        String defaultCountry = "USA";

        LongLat longLat = new LongLat();

        String cityStateCountry = city + "," + stateCode + "," + defaultCountry;
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI("http://api.openweathermap.org/geo/1.0/direct?q=" + cityStateCountry + "&limit=1&appid=7052942a75bae80506bde8d3037fca39")).GET().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonArray jsonArray = JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        longLat.setLatitude(jsonObject.get("lat").getAsString());
        longLat.setLongitude(jsonObject.get("lon").getAsString());

        return longLat;
    }

    public JsonObject retrieveWeatherByLongLat(LongLat longLat) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI("http://api.openweathermap.org/data/2.5/forecast?lat=" + longLat.getLatitude() + "&lon=" + longLat.getLongitude() + "&appid=7052942a75bae80506bde8d3037fca39&units=imperial")).GET().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }
}
