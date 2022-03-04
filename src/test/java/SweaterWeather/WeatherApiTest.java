package SweaterWeather;

import SweaterWeather.Model.LongLat;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.URISyntaxException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeatherApiTest {

    WeatherApi weatherApi;
    String state = "OH";
    String city = "Columbus";

    LongLat testLongLat;
    String testLon = "39.9622601";
    String testLat = "-83.0007065";

    @BeforeAll
    public void setup() {
        weatherApi = new WeatherApi();
        testLongLat = new LongLat();
        testLongLat.setLongitude(testLon);
        testLongLat.setLatitude(testLat);
    }

    @Test
    public void testRetrieveLongLatReturnsCorrectLongLat() throws URISyntaxException, IOException, InterruptedException {
        LongLat result = weatherApi.retrieveLongLat(city, state);

        Assertions.assertEquals("39.9622601", result.getLatitude());
        Assertions.assertEquals("-83.0007065", result.getLongitude());
    }

    @Test
    public void testRetrieveWeatherReturnsJsonWithCode200() throws IOException, InterruptedException, URISyntaxException {
        JsonObject result = weatherApi.retrieveWeatherByLongLat(testLongLat);

        Assertions.assertEquals("200", result.get("cod").getAsString());
    }

    @Test
    public void testGetWeatherByCityState() throws URISyntaxException, IOException, InterruptedException {
        JsonObject result = weatherApi.getWeatherJsonByCityState(city, state);

        Assertions.assertEquals("200", result.get("cod").getAsString());
    }

    @Test
    public void testGetWeatherByCityStateWhereCityHasSpace() throws URISyntaxException, IOException, InterruptedException {
        city = "New York";
        state = "NY";
        JsonObject result = weatherApi.getWeatherJsonByCityState(city, state);

        Assertions.assertEquals("200", result.get("cod").getAsString());
    }

}
