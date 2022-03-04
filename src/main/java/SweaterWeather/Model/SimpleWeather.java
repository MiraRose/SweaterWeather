package SweaterWeather.Model;

public class SimpleWeather {

    String outlook;
    double minTemp;
    double maxTemp;

    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public boolean isWetWeather() {
        return outlook.contains("Rain") || outlook.contains("Snow");
    }
}
