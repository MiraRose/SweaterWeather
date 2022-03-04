package SweaterWeather;

import SweaterWeather.Model.SimpleWeather;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class SimpleWeatherBuilderTest {

    SimpleWeatherBuilder simpleWeatherBuilder;

    BufferedReader reader;

    String testString;

    public SimpleWeatherBuilderTest() {
    }

    @Before
    public void setup() throws IOException {
        simpleWeatherBuilder = new SimpleWeatherBuilder();
        reader = new BufferedReader(new FileReader("src/test/resources/test_weather.txt"));
        testString = reader.readLine();

    }

    @Test
    public void testBuildsSimpleWeather() {
        JsonObject jsonObject = JsonParser.parseString(testString).getAsJsonObject();
        SimpleWeather result = simpleWeatherBuilder.build(jsonObject);

        Assert.assertEquals(34, result.getMaxTemp(), 0);
        Assert.assertEquals(31.77, result.getMinTemp(), 0);
        Assert.assertEquals("Clear", result.getOutlook());
    }
}
