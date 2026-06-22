public class Cloudly extends CurrentWeather {
    public Cloudly(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        weatherType = WeatherEnum.CLOUDY;
    }

    @Override
    public String toString() {
        return String.format(
                "天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 風速=%.1f m/s",
                weatherType, time, time, currentWeatherdData.mainTemp, currentWeatherdData.windSpeed
        );
    }
}
