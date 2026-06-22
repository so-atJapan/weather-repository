public class Rain extends CurrentWeather {
    public Rain(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        weatherType = WeatherEnum.RAIN;
    }

    public void show(){
        System.out.println();
    }
}
