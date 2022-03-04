package SweaterWeather;

import SweaterWeather.Model.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigReaderTest {
    ConfigReader configReader;

    @BeforeAll
    public void setup() {
        configReader = new ConfigReader();
    }

    @Test
    public void testReturnsCorrectConfig() throws IOException {
        Configuration configuration = configReader.read("src/test/resources/test_config.json");

        Assertions.assertEquals(7, configuration.getRecommendations().size());
        Assertions.assertEquals(100, configuration.getRecommendations().get(0).getMaxTemp(), 0);
        Assertions.assertEquals(75, configuration.getRecommendations().get(0).getMinTemp(), 0);
        Assertions.assertEquals("Sunglasses", configuration.getRecommendations().get(0).getName());
        Assertions.assertFalse(configuration.getRecommendations().get(0).isWaterproof());
    }
}
