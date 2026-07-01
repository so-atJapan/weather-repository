/**
 * 曇りまたは悪天候の天気状態を表すクラス。
 * 
 * Weatherの具体的な実装として、曇り・霧・霞などの
 * 天気データから生成されます。気圧に応じて、低気圧による
 * 頭痛への注意または紫外線への注意を表示します。
 * 
 * 気圧による警告判定：
 * - 1000hPa以下：低気圧による頭痛に気をつけてください
 * - 1000hPa超：曇っていても、紫外線が強いので気をつけてください
 * 
 * 対応する天気タイプ：
 * - Mist（霧）
 * - Smoke（煙）
 * - Haze（霞）
 * - Dust（塵）
 * - Fog（濃い霧）
 * - Sand（砂塵）
 * - Ash（火山灰）
 * - Clouds（曇り）
 * 
 * @author Weather Application Team
 * @version 1.0
 * @see Weather
 * @see CurrentWeatherData
 */
public class Cloudy extends Weather {

    /**
     * 大気圧を保持するフィールド。
     * 
     * OpenWeatherAPIから取得した pressure の値（hPa）です。
     * 低気圧と高気圧の判定（1000hPa）に使用されます。
     * 初期値は0です。
     */
    private int pressure = 0;

    /**
     * Cloudyクラスのコンストラクタ。
     * 
     * 親クラスのコンストラクタを呼び出し、
     * 天気タイプを「曇り」に設定し、
     * 大気圧を取得します。
     * 
     * @param currentWeatherData OpenWeatherAPIから取得した天気データオブジェクト
     * 
     * @throws NullPointerException currentWeatherDataがnullの場合
     * 
     * @see Weather#Weather(CurrentWeatherData)
     */
    public Cloudy(CurrentWeatherData currentWeatherData) {

        super(currentWeatherData);
        this.weatherType = "曇り";
        this.pressure    = currentWeatherData.mainPressure;

    }

    /**
     * 大気圧に基づいた健康警告メッセージを取得します。
     * 
     * 大気圧により以下の2パターンでメッセージを返します：
     * - 1000hPa以下：「低気圧による頭痛に気をつけてください。」
     * - 1000hPa超：「曇っていても、紫外線が強いので気をつけてください。」
     * 
     * @return 大気圧に応じた警告メッセージ
     * 
     * @since 1.0
     * @see #pressure
     */
    @Override
    public String getMessage() {

        if (this.pressure <= 1000) {
            message = "低気圧による頭痛に気をつけてください。";
        } else {
            message = "曇っていても、紫外線が強いので気をつけてください。";
        }
        return message;

    }

    /**
     * 曇りの天気における詳細な天気情報を取得します。
     * 
     * 以下の情報をフォーマットして返します：
     * - 場所（city name）
     * - 天気情報（「曇り」）
     * - 日時（yyyy-MM-dd HH:mm:ss 形式）
     * - 気温（℃）
     * - 湿度（%）
     * - 気圧（hPa）
     * - 風速（m/s）
     * 
     * @return フォーマットされた天気情報文字列
     *         例："場所：Osaka / 天気情報：曇り / 時刻=2026-07-01 14:30:45 / 気温=22.3℃ / 湿度=70% / 気圧=1005 hPa / 風速=2.8 m/s"
     * 
     * @since 1.0
     * @see Weather#getData()
     */
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
