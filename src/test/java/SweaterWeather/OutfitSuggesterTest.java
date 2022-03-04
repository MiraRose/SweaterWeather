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

        configuration.addRecommendation(waterProofItem);

        simpleWeather = new SimpleWeather();

    }

    @Test
    public void testWhenGivenRainInOutlookSuggestsWaterproofItem() {
        simpleWeather.setOutlook("Rain");
        List<String> result = outfitSuggester.suggestOutfitList(configuration, simpleWeather);

        Assert.assertEquals("Rain Jacket", result.get(0));
    }
}
