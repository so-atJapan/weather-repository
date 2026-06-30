public class Cloudy extends Weather {

    private int pressure = 0;

    public Cloudy(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        this.weatherType = "曇り";
        this.pressure    = currentWeatherData.mainPressure;
    }

    @Override
    public String getMessage() {
        if (this.pressure <= 1000) {
            message = "低気圧による頭痛に気をつけてください。";
        } else {
            message = "曇っていても、紫外線が強いので気をつけてください。";
        }
        return message;
    }

    @Override
    public String getData() {
        return String.format(
                "場所：%s / 天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 湿度=%d%% / 気圧=%d hPa / 風速=%.1f m/s",
                currentWeatherdData.name,
                weatherType,
                time,
                time,
                currentWeatherdData.mainTemp,
                currentWeatherdData.mainHumidity,
                currentWeatherdData.mainPressure,
                currentWeatherdData.windSpeed
        );
    }

}
