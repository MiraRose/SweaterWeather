package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.Recommendation;
import SweaterWeather.Model.SimpleWeather;

import java.util.ArrayList;
import java.util.List;

public class OutfitSuggester {

    public List<String> suggestOutfitList(Configuration configuration, SimpleWeather simpleWeather) {
        List<String> items = new ArrayList<>();

        for (Recommendation recommendation : configuration.getRecommendations()) {

            if (isTempWithRange(recommendation, simpleWeather)
                    && simpleWeather.isWetWeather()
                    && recommendation.isWaterproof()) {
                items.add(recommendation.getName());
            } else if (isTempWithRange(recommendation, simpleWeather)
                    && !simpleWeather.isWetWeather()
                    && !recommendation.isWaterproof()) {
                items.add(recommendation.getName());
            }
        }

        // if no items, ditch waterproof check and add any items within temp range
        if (items.size() == 0) {
            for (Recommendation recommendation : configuration.getRecommendations()) {
                if (isTempWithRange(recommendation, simpleWeather)) {
                    items.add(recommendation.getName());
                }
            }
        }

        // if still no items at 'no items suggested'
        if (items.size() == 0) {
            items.add("No Items Suggested");
        }

        return items;
    }

    private boolean isTempWithRange(Recommendation recommendation, SimpleWeather simpleWeather) {
        return recommendation.getMaxTemp() >= simpleWeather.getMaxTemp() && recommendation.getMinTemp() <= simpleWeather.getMinTemp();
    }
}
