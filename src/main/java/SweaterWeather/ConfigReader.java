package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.Recommendation;
import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigReader {

    public Configuration read(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
        JsonArray availableRecommendations = jsonObject.get("available_recommendations").getAsJsonArray();
        Configuration configuration = new Configuration();

        for (JsonElement element : availableRecommendations) {
            JsonObject recommendationJson = element.getAsJsonObject();
            Recommendation recommendation = new Recommendation();
            recommendation.setMinTemp(recommendationJson.get("min_temp").getAsDouble());
            recommendation.setMaxTemp(recommendationJson.get("max_temp").getAsDouble());
            recommendation.setWaterproof(recommendationJson.get("waterproof").getAsBoolean());
            recommendation.setName(recommendationJson.get("name").getAsString());
            configuration.addRecommendation(recommendation);
        }

        return configuration;
    }
}
