package SweaterWeather;

import SweaterWeather.Model.LongLat;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherApiTest {

    WeatherApi weatherApi;
    String state = "OH";
    String city = "Columbus";

    LongLat testLongLat;
    String testLon = "39.9622601";
    String testLat = "-83.0007065";

    @Before
    public void setup() {
        weatherApi = new WeatherApi();
        testLongLat = new LongLat();
        testLongLat.setLongitude(testLon);
        testLongLat.setLatitude(testLat);
    }

    @Test
    public void testRetrieveLongLatReturnsCorrectLongLat() throws URISyntaxException, IOException, InterruptedException {
        LongLat result = weatherApi.retrieveLongLat(city, state);

        Assert.assertEquals("39.9622601", result.getLatitude());
        Assert.assertEquals("-83.0007065", result.getLongitude());
    }

    @Test
    public void testRetrieveWeatherReturnsJsonWithCode200() throws IOException, InterruptedException, URISyntaxException {
        JsonObject result = weatherApi.retrieveWeatherByLongLat(testLongLat);

        Assert.assertEquals("200", result.get("cod").getAsString());
    }

    @Test
    public void testGetWeatherByCityState() throws URISyntaxException, IOException, InterruptedException {
        JsonObject result = weatherApi.getWeatherJsonByCityState(city, state);

        Assert.assertEquals("200", result.get("cod").getAsString());
    }

}
