package SweaterWeather;

import SweaterWeather.Model.Configuration;
import SweaterWeather.Model.Recommendation;
import SweaterWeather.Model.SimpleWeather;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OutfitSuggesterTest {

    OutfitSuggester outfitSuggester;
    Configuration configuration;
    SimpleWeather simpleWeather;

    @Before
    public void setup() {
        outfitSuggester = new OutfitSuggester();

        configuration = new Configuration();
        Recommendation waterProofItem = new Recommendation();
        waterProofItem.setWaterproof(true);
        waterProofItem.setName("Rain Jacket");
        waterProofItem.setMaxTemp(80);
        waterProofItem.setMinTemp(62);

        Recommendation hotTempItem = new Recommendation();
        hotTempItem.setWaterproof(false);
        hotTempItem.setMinTemp(75);
        hotTempItem.setMaxTemp(100);
        hotTempItem.setName("Sunglasses");

        Recommendation coldSnowItem = new Recommendation();
        coldSnowItem.setWaterproof(true);
        coldSnowItem.setName("Heavy Coat");
        coldSnowItem.setMaxTemp(40);
        coldSnowItem.setMinTemp(0);

        configuration.addRecommendation(waterProofItem);
        configuration.addRecommendation(hotTempItem);
        configuration.addRecommendation(coldSnowItem);

        simpleWeather = new SimpleWeather();

    }

    @Test
    public void testWhenGivenRainInOutlookWithTempInRangeSuggestsWaterproofItem() {
        simpleWeather.setOutlook("Rain");
        simpleWeather.setMinTemp(65);
        simpleWeather.setMaxTemp(70);
        List<String> result = outfitSuggester.suggestOutfitList(configuration, simpleWeather);

        Assert.assertTrue(result.size() > 0);
        Assert.assertEquals("Rain Jacket", result.get(0));
    }

    @Test
    public void testWhenGivenSnowInOutlookWithTempInRangeSuggestsWaterproofItem() {
        simpleWeather.setOutlook("Snow");
        simpleWeather.setMinTemp(2);
        simpleWeather.setMaxTemp(10);
        List<String> result = outfitSuggester.suggestOutfitList(configuration, simpleWeather);

        Assert.assertTrue(result.size() > 0);
        Assert.assertEquals("Heavy Coat", result.get(0));
    }

    @Test
    public void testWhenGivenTempRangeSuggestsItemWithinTempRange() {
        simpleWeather.setOutlook("Sunny");
        simpleWeather.setMaxTemp(95);
        simpleWeather.setMinTemp(75);
        List<String> result = outfitSuggester.suggestOutfitList(configuration, simpleWeather);

        Assert.assertTrue(result.size() > 0);
        Assert.assertEquals("Sunglasses", result.get(0));
    }
}
