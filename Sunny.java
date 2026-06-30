public class Sunny extends Weather {

    private double feelLike = 0.0;

    public Sunny(CurrentWeatherData currentWeatherData){
        super(currentWeatherData);
        this.weatherType = "晴れ";
        this.feelLike    = currentWeatherData.mainFeelLike;
    }

    @Override
    public String getMessage() {
        if (this.feelLike >= 35) {
            message = "熱中症に厳重警戒";
        } else if (this.feelLike >= 31) {
            message = "熱中症に注意";
        } else {
            message = "紫外線に気を付けてください。";
        }
        return message;
    }

    @Override
    public String getData() {
        return String.format(
                "場所：%s / 天気情報：%s / 時刻=%tF %tT / 気温=%.1f℃ / 湿度=%d%% / 風速=%.1f m/s / 体感温度=%.1f℃",
                currentWeatherdData.name,
                weatherType,
                time,
                time,
                currentWeatherdData.mainTemp,
                currentWeatherdData.mainHumidity,
                currentWeatherdData.windSpeed,
                feelLike
        );
    }

}
