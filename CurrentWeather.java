import java.util.Calendar;

public abstract class CurrentWeather extends Object {
    protected WeatherEnum weather = null;
    protected Calendar time = Calendar.getInstance();
    protected double temperature = 0.0;
    protected double windSpeed = 0.0;
    protected double chanceOfRain = 0.0;

    @Override
    public String toString() {
        return String.format(
                "天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 風速=%.1f m/s",
                weather, time, time, temperature, windSpeed
        );
    }
    
    public static CurrentWeather getInstance(){

        CurrentWeatherData weather = OpenWeatherAPI.getCurrentWeather();

        switch (weather.weatherMain) {
            case "Thunderstorm":
                return new Sunny();
            case "Drizzle":
                return new Sunny();
            case "Rain":
                return new Sunny();
            case "Snow":
                return new Sunny();
            case "Mist":
                return new Sunny();
            case "Smoke":
                return new Sunny();
            case "Haze":
                return new Sunny();
            case "Dust":
                return new Sunny();
            case "Fog":
                return new Sunny();
            case "Sand":
                return new Sunny();
            case "Ash":
                return new Sunny();
            case "Squall":
                return new Sunny();
            case "Tornado":
                return new Sunny();
            case "Clear":
                return new Sunny();
            case "Clouds":
                return new Sunny();
            default:
                return null;
        }

        
    }


}
