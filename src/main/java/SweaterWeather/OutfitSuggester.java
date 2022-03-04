package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.Recommendation;
import SweaterWeather.Model.SimpleWeather;

import java.util.ArrayList;
import java.util.List;

public class OutfitSuggester {

    public List<String> suggestOutfitList(Configuration configuration, SimpleWeather simpleWeather) {
        List<String> outfits = new ArrayList<>();

        if (simpleWeather.getOutlook().contains("Rain")) {
            for (Recommendation recommendation : configuration.getRecommendations()) {
                if (recommendation.isWaterproof()) {
                    outfits.add(recommendation.getName());
                }
            }
        }

        return outfits;
    }
}
