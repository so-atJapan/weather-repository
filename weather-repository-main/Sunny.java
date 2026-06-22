public class Sunny extends CurrentWeather {
    public Sunny(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        weatherType = WeatherEnum.SUNNY;
    }

    @Override
    public String toString() {
        return String.format(
                "天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 風速=%.1f m/s",
                weatherType, time, time, currentWeatherdData.mainTemp, currentWeatherdData.windSpeed
        );
    }
}
