public class Rain extends Weather {

    private int    pressure      = 0;
    private double precipitation = 0.0;

    public Rain(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        this.weatherType   = "雨";
        this.pressure      = currentWeatherData.mainPressure;
        this.precipitation = currentWeatherData.rain1h;
    }

    @Override
    public String getMessage() {
        message = "滑らないよう、足元に気をつけてください。";
        return message;
    }

    @Override
    public String getData() {
        return String.format(
                "場所：%s / 天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 湿度=%d%% / 気圧=%d hPa / 風速=%.1f m/s / 降水量=%.1f mm",
                currentWeatherdData.name,
                weatherType,
                time,
                time,
                currentWeatherdData.mainTemp,
                currentWeatherdData.mainHumidity,
                pressure,
                currentWeatherdData.windSpeed,
                precipitation
        );
    }
}
