import java.util.Calendar;

public abstract class Weather extends Object {
    protected String weatherType = null;
    protected CurrentWeatherData currentWeatherdData = null;
    protected Calendar time = Calendar.getInstance();

    protected String message = "no message";

    Weather(CurrentWeatherData currentWeatherData){
        this.currentWeatherdData = currentWeatherData;
    }

    public static Weather getInstance(){

        CurrentWeatherData weather = OpenWeatherAPI.getCurrentWeather("Osaka");
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
                return new Cloudy(weather);
            case "Smoke":
                return new Cloudy(weather);
            case "Haze":
                return new Cloudy(weather);
            case "Dust":
                return new Cloudy(weather);
            case "Fog":
                return new Cloudy(weather);
            case "Sand":
                return new Cloudy(weather);
            case "Ash":
                return new Cloudy(weather);
            case "Squall":
                return new Rain(weather);
            case "Tornado":
                return new Rain(weather);
            case "Clear":
                return new Sunny(weather);
            case "Clouds":
                return new Cloudy(weather);
            default:
                return null;
        }
    
    }

    public void perform(){
        StringBuilder info = new StringBuilder();
        info.append(this);
        info.append(System.lineSeparator());
        info.append(this.getMessage());
        System.out.println(info.toString());
    };

    public abstract String getMessage();
    
    public abstract String getData(); 

    @Override
    public String toString() {
        return this.getData();
    }

}
