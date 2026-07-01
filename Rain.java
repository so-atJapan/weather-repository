/**
 * 雨の天気状態を表すクラス。
 * 
 * Weatherの具体的な実装として、雨・雪・雷などの降水関連の
 * 天気データから生成されます。常に「足元に気をつけてください」
 * というメッセージを表示し、降水量も含めた詳細な天気情報を提供します。
 * 
 * 対応する天気タイプ：
 * - Thunderstorm（雷雨）
 * - Drizzle（霧雨）
 * - Rain（雨）
 * - Snow（雪）
 * - Squall（突風）
 * - Tornado（竜巻）
 * 
 * @author Weather Application Team
 * @version 1.0
 * @see Weather
 * @see CurrentWeatherData
 */
public class Rain extends Weather {

    /**
     * 大気圧を保持するフィールド。
     * 
     * OpenWeatherAPIから取得した pressure の値（hPa）です。
     * 現在のこの実装では使用されていませんが、将来的な
     * 気圧変化による警告メッセージの追加に対応します。
     * 初期値は0です。
     */
    private int    pressure      = 0;
    
    /**
     * 降水量を保持するフィールド。
     * 
     * OpenWeatherAPIから取得した 1時間の降水量（mm）です。
     * 天気情報の表示に含まれます。
     * 初期値は0.0です。
     */
    private double precipitation = 0.0;

    /**
     * Rainクラスのコンストラクタ。
     * 
     * 親クラスのコンストラクタを呼び出し、
     * 天気タイプを「雨」に設定し、
     * 大気圧と降水量を取得します。
     * 
     * @param currentWeatherData OpenWeatherAPIから取得した天気データオブジェクト
     * 
     * @throws NullPointerException currentWeatherDataがnullの場合
     * 
     * @see Weather#Weather(CurrentWeatherData)
     */
    public Rain(CurrentWeatherData currentWeatherData) {

        super(currentWeatherData);
        this.weatherType   = "雨";
        this.pressure      = currentWeatherData.mainPressure;
        this.precipitation = currentWeatherData.rain1h;

    }

    /**
     * 雨の日の安全に関するメッセージを取得します。
     * 
     * 常に「滑らないよう、足元に気をつけてください。」というメッセージを返します。
     * 雨の日は路面が滑りやすくなるため、安全への注意を促すメッセージです。
     * 
     * @return \"滑らないよう、足元に気をつけてください。\"
     * 
     * @since 1.0
     */
    @Override
    public String getMessage() {

        message = "滑らないよう、足元に気をつけてください。";
        return message;

    }

    /**
     * 雨の天気における詳細な天気情報を取得します。
     * 
     * 以下の情報をフォーマットして返します：
     * - 場所（city name）
     * - 天気情報（「雨」）
     * - 日時（yyyy-MM-dd HH:mm:ss 形式）
     * - 気温（℃）
     * - 湿度（%）
     * - 気圧（hPa）
     * - 風速（m/s）
     * - 降水量（mm）
     * 
     * @return フォーマットされた天気情報文字列
     *         例："場所：Osaka / 天気情報：雨 / 時刻=2026-07-01 14:30:45 / 気温=18.5℃ / 湿度=85% / 気圧=1010 hPa / 風速=5.2 m/s / 降水量=2.5 mm"
     * 
     * @since 1.0
     * @see Weather#getData()
     */
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
