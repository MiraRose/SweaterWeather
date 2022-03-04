package SweaterWeather.Model;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    List<Recommendation> recommendations = new ArrayList<>();

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void addRecommendation(Recommendation recommendation) {
        recommendations.add(recommendation);
    }
}
