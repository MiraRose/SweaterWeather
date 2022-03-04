package SweaterWeather;

import SweaterWeather.Model.SimpleWeather;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SimpleWeatherBuilder {

    public SimpleWeather build(JsonObject jsonObject) {
        SimpleWeather simpleWeather = new SimpleWeather();
        JsonArray weatherList = jsonObject.get("list").getAsJsonArray();
        JsonObject todayWeather = weatherList.get(0).getAsJsonObject();
        JsonObject mainWeather = todayWeather.get("main").getAsJsonObject();
        double minTemp = mainWeather.get("temp_min").getAsDouble();
        double maxTemp = mainWeather.get("temp_max").getAsDouble();

        JsonArray weatherDescripList = todayWeather.get("weather").getAsJsonArray();
        JsonObject weatherDescrip = weatherDescripList.get(0).getAsJsonObject();
        String weatherOutlook = weatherDescrip.get("main").getAsString();

        simpleWeather.setMinTemp(minTemp);
        simpleWeather.setMaxTemp(maxTemp);
        simpleWeather.setOutlook(weatherOutlook);

        return simpleWeather;
    }
}
