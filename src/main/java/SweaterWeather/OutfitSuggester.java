package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.Recommendation;
import SweaterWeather.Model.SimpleWeather;

import java.util.ArrayList;
import java.util.List;

public class OutfitSuggester {

    public List<String> suggestOutfitList(Configuration configuration, SimpleWeather simpleWeather) {
        List<String> outfits = new ArrayList<>();

        for (Recommendation recommendation : configuration.getRecommendations()) {

            if (isTempWithRange(recommendation, simpleWeather)
                    && simpleWeather.isWetWeather()
                    && recommendation.isWaterproof()) {
                outfits.add(recommendation.getName());
            } else if (isTempWithRange(recommendation, simpleWeather)) {
                outfits.add(recommendation.getName());
            }
        }

        return outfits;
    }

    private boolean isTempWithRange(Recommendation recommendation, SimpleWeather simpleWeather) {
        return recommendation.getMaxTemp() >= simpleWeather.getMaxTemp() && recommendation.getMinTemp() <= simpleWeather.getMinTemp();
    }
}
