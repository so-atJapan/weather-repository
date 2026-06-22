import java.util.Calendar;

public abstract class CurrentWeather extends Object {
    protected WeatherEnum weatherType = null;
    protected CurrentWeatherData currentWeatherdData = null;
    protected Calendar time = Calendar.getInstance();

    CurrentWeather(CurrentWeatherData currentWeatherData){
        this.currentWeatherdData = currentWeatherData;
    }

    @Override
    public String toString() {
        return String.format(
                "場所：%s / 天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 湿度=%d%% / 風速=%.1f m/s",
                currentWeatherdData.name, weatherType, time, time, currentWeatherdData.mainTemp,currentWeatherdData.mainHumidity, currentWeatherdData.windSpeed
        );
    }
    
    public static CurrentWeather getInstance(){

        CurrentWeatherData weather = OpenWeatherAPI.getCurrentWeather();

        switch (weather.weatherMain) {
            case "Thunderstorm":
                return new Rain(weather);
            case "Drizzle":
                return new Rain(weather);
            case "Rain":
                return new Rain(weather);
            case "Snow":
                return new Rain(weather);
            case "Mist":
                return new Cloudly(weather);
            case "Smoke":
                return new Cloudly(weather);
            case "Haze":
                return new Cloudly(weather);
            case "Dust":
                return new Cloudly(weather);
            case "Fog":
                return new Cloudly(weather);
            case "Sand":
                return new Cloudly(weather);
            case "Ash":
                return new Cloudly(weather);
            case "Squall":
                return new Rain(weather);
            case "Tornado":
                return new Rain(weather);
            case "Clear":
                return new Sunny(weather);
            case "Clouds":
                return new Cloudly(weather);
            default:
                return null;
        }

        
    }


}
