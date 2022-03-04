package SweaterWeather;

import SweaterWeather.Model.LongLat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherApiTest {

    WeatherApi weatherApi;
    String state = "OH";
    String city = "Columbus";

    @Before
    public void setup() {
        weatherApi = new WeatherApi();
    }

    @Test
    public void testRetrieveLongLatReturnsCorrectLongLat() throws URISyntaxException, IOException, InterruptedException {
        LongLat result = weatherApi.retrieveLongLat(city, state);

        Assert.assertEquals("39.9622601", result.getLatitude());
        Assert.assertEquals("-83.0007065", result.getLongitude());
    }

}
