package SweaterWeather;

import SweaterWeather.Model.SimpleWeather;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleWeatherBuilderTest {

    SimpleWeatherBuilder simpleWeatherBuilder;

    BufferedReader reader;

    String testString;

    public SimpleWeatherBuilderTest() {
    }

    @BeforeAll
    public void setup() throws IOException {
        simpleWeatherBuilder = new SimpleWeatherBuilder();
        reader = new BufferedReader(new FileReader("src/test/resources/test_weather.txt"));
        testString = reader.readLine();

    }

    @Test
    public void testBuildsSimpleWeather() {
        JsonObject jsonObject = JsonParser.parseString(testString).getAsJsonObject();
        SimpleWeather result = simpleWeatherBuilder.build(jsonObject);

        Assertions.assertEquals(34, result.getMaxTemp(), 0);
        Assertions.assertEquals(31.77, result.getMinTemp(), 0);
        Assertions.assertEquals("Clear", result.getOutlook());
    }
}
