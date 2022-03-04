package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.SimpleWeather;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class App {




    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        WeatherApi weatherApi = new WeatherApi();
        ConfigReader configReader = new ConfigReader();
        SimpleWeatherBuilder simpleWeatherBuilder = new SimpleWeatherBuilder();
        OutfitSuggester outfitSuggester = new OutfitSuggester();

        JsonObject weather = weatherApi.getWeatherJsonByCityState(args[1], args[2]);
        SimpleWeather simpleWeather = simpleWeatherBuilder.build(weather);
        Configuration configuration = configReader.read(args[0]);

        List<String> outfits = outfitSuggester.suggestOutfitList(configuration, simpleWeather);
        System.out.println("Suggested Clothing Items to Bring");
        for (String outfit : outfits) {
            System.out.println(outfit);
        }
    }
}
