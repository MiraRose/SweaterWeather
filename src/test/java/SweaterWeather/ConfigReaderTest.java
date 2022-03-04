package SweaterWeather;

import SweaterWeather.Model.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ConfigReaderTest {
    ConfigReader configReader;

    @Before
    public void setup() {
        configReader = new ConfigReader();
    }

    @Test
    public void testReturnsCorrectConfig() throws IOException {
        Configuration configuration = configReader.read("src/test/resources/test_config.json");

        Assert.assertEquals(7, configuration.getRecommendations().size());
        Assert.assertEquals(100, configuration.getRecommendations().get(0).getMaxTemp(), 0);
        Assert.assertEquals(75, configuration.getRecommendations().get(0).getMinTemp(), 0);
        Assert.assertEquals("Sunglasses", configuration.getRecommendations().get(0).getName());
        Assert.assertFalse(configuration.getRecommendations().get(0).isWaterproof());
    }
}
