/**
 * 晴れの天気状態を表すクラス。
 * 
 * Weatherの具体的な実装として、晴れ（Clear）の天気データから
 * 生成されます。体感温度を基に、熱中症の警戒レベルを判定して
 * 適切なメッセージを表示します。
 * 
 * 体感温度による警告判定：
 * - 35℃以上：熱中症に厳重警戒
 * - 31℃～35℃未満：熱中症に注意
 * - 31℃未満：紫外線に気をつけてください
 * 
 * @author Weather Application Team
 * @version 1.0
 * @see Weather
 * @see CurrentWeatherData
 */
public class Sunny extends Weather {

    /**
     * 体感温度を保持するフィールド。
     * 
     * OpenWeatherAPIから取得した feels_like の値です。
     * 熱中症警戒レベルの判定に使用されます。
     * 初期値は0.0です。
     */
    private double feelLike = 0.0;

    /**
     * Sunnyクラスのコンストラクタ。
     * 
     * 親クラスのコンストラクタを呼び出し、
     * 天気タイプを「晴れ」に設定し、
     * 体感温度（feels_like）を取得します。
     * 
     * @param currentWeatherData OpenWeatherAPIから取得した天気データオブジェクト
     * 
     * @throws NullPointerException currentWeatherDataがnullの場合
     * 
     * @see Weather#Weather(CurrentWeatherData)
     */
    public Sunny(CurrentWeatherData currentWeatherData) {

        super(currentWeatherData);
        this.weatherType = "晴れ";
        this.feelLike    = currentWeatherData.mainFeelLike;

    }

    /**
     * 体感温度に基づいた熱中症警戒レベルのメッセージを取得します。
     * 
     * 体感温度により以下の3段階でメッセージを返します：
     * - 35℃以上：「熱中症に厳重警戒」
     * - 31℃～35℃未満：「熱中症に注意」
     * - 31℃未満：「紫外線に気をつけてください。」
     * 
     * @return 体感温度に応じた警告メッセージ
     * 
     * @since 1.0
     * @see #feelLike
     */
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

    /**
     * 晴れの天気における詳細な天気情報を取得します。
     * 
     * 以下の情報をフォーマットして返します：
     * - 場所（city name）
     * - 天気情報（「晴れ」）
     * - 日時（yyyy-MM-dd HH:mm:ss 形式）
     * - 気温（℃）
     * - 湿度（%）
     * - 風速（m/s）
     * - 体感温度（℃）
     * 
     * @return フォーマットされた天気情報文字列
     *         例："場所：Osaka / 天気情報：晴れ / 時刻=2026-07-01 14:30:45 / 気温=32.5℃ / 湿度=60% / 風速=3.2 m/s / 体感温度=35.1℃"
     * 
     * @since 1.0
     * @see Weather#getData()
     */
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
