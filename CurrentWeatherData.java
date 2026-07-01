/**
 * OpenWeatherAPI から取得した天気データを保持するデータクラス。
 * 
 * OpenWeatherAPI の JSON レスポンスを手動パースして、
 * 各天気情報を対応するフィールドに格納します。
 * 座標、天気状況、気温、気圧、湿度、風速など、
 * 詳細な気象データを保持する包括的なデータモデルです。
 * 
 * 主なデータグループ：
 * - 座標情報：緯度・経度
 * - 天気情報：id、メイン天気、詳細説明、アイコン
 * - 主要気象データ：気温、体感温度、最高/最低気温、気圧、湿度
 * - 風情報：風速、風向、突風速度
 * - その他：視界、雲量、降水量、日時、国、日の出/日没時刻
 * 
 * @author OpenWeatherAPI
 * @version 1.0
 * @see OpenWeatherAPI#getCurrentWeather()
 * @see Weather
 */
public class CurrentWeatherData extends Object {

    /**
     * 地点の経度（Longitude）。
     * 
     * OpenWeatherAPI の coord.lon から取得されます。
     * 大阪の場合、通常135.5～136.5の範囲です。
     */
    protected double coordLon;
    
    /**
     * 地点の緯度（Latitude）。
     * 
     * OpenWeatherAPI の coord.lat から取得されます。
     * 大阪の場合、通常34.5～34.8の範囲です。
     */
    protected double coordLat;

    /**
     * 天気の一意のID。
     * 
     * OpenWeatherAPI の weather[0].id から取得されます。
     * 天気タイプを特定するための内部IDです。
     */
    protected int weatherId;
    
    /**
     * 天気の主カテゴリ。
     * 
     * OpenWeatherAPI の weather[0].main から取得されます。
     * 例："Clear"、"Clouds"、"Rain"、"Thunderstorm" など。
     * Weather クラスの getInstance() メソッドでこの値により
     * 適切な天気オブジェクト（Sunny、Cloudy、Rain）を生成します。
     */
    protected String weatherMain;
    
    /**
     * 天気の詳細説明。
     * 
     * OpenWeatherAPI の weather[0].description から取得されます。
     * 日本語での詳細な天気説明です。
     * 例："晴れ"、"曇り"、"小雨" など。
     */
    protected String weatherDescription;
    
    /**
     * 天気を表すアイコンID。
     * 
     * OpenWeatherAPI の weather[0].icon から取得されます。
     * OpenWeatherAPI のアイコン URL に使用される識別子です。
     */
    protected String weatherIcon;
    
    /**
     * データソースの種別。
     * 
     * OpenWeatherAPI の base から取得されます。
     * 通常 "stations" や "stations,gfs" などの値です。
     */
    protected String base;

    /**
     * 現在の気温。
     * 
     * OpenWeatherAPI の main.temp から取得されます。
     * 単位は摂氏温度（℃）です。
     */
    protected double mainTemp;
    
    /**
     * 体感温度。
     * 
     * OpenWeatherAPI の main.feels_like から取得されます。
     * 風や湿度を考慮した、人間が感じる温度です（℃）。
     */
    protected double mainFeelLike;
    
    /**
     * 最低気温。
     * 
     * OpenWeatherAPI の main.temp_min から取得されます。
     * 単位は摂氏温度（℃）です。
     */
    protected double mainTempMin;
    
    /**
     * 最高気温。
     * 
     * OpenWeatherAPI の main.temp_max から取得されます。
     * 単位は摂氏温度（℃）です。
     */
    protected double mainTempMax;
    
    /**
     * 大気圧。
     * 
     * OpenWeatherAPI の main.pressure から取得されます。
     * 単位はヘクトパスカル（hPa）です。
     * 低気圧（≦1000hPa）の判定に使用されます。
     */
    protected int mainPressure;
    
    /**
     * 湿度。
     * 
     * OpenWeatherAPI の main.humidity から取得されます。
     * 単位はパーセント（%）、0～100の範囲です。
     */
    protected int mainHumidity;
    
    /**
     * 海面気圧。
     * 
     * OpenWeatherAPI の main.sea_level から取得されます。
     * 単位はヘクトパスカル（hPa）です。
     */
    protected int mainSeaLevel;
    
    /**
     * 地面気圧。
     * 
     * OpenWeatherAPI の main.grnd_level から取得されます。
     * 単位はヘクトパスカル（hPa）です。
     */
    protected int mainGrnd_Level;

    /**
     * 視界距離。
     * 
     * OpenWeatherAPI の visibility から取得されます。
     * 単位はメートル（m）です。
     */
    protected int visibility;

    /**
     * 風速。
     * 
     * OpenWeatherAPI の wind.speed から取得されます。
     * 単位は秒速メートル（m/s）です。
     */
    protected double windSpeed;
    
    /**
     * 風向。
     * 
     * OpenWeatherAPI の wind.deg から取得されます。
     * 単位は度（°）、0～360の範囲です。
     * 0＝北、90＝東、180＝南、270＝西。
     */
    protected int windDeg;
    
    /**
     * 突風速度。
     * 
     * OpenWeatherAPI の wind.gust から取得されます。
     * 単位は秒速メートル（m/s）です。
     */
    protected double windGust;
    
    /**
     * 雲量。
     * 
     * OpenWeatherAPI の clouds.all から取得されます。
     * 単位はパーセント（%）、0～100の範囲です。
     */
    protected int CloudsAll;

    /**
     * データの取得時刻（UNIXタイムスタンプ）。
     * 
     * OpenWeatherAPI の dt から取得されます。
     * 秒単位の経過時間です。
     */
    protected int dt;

    /**
     * システムデータのタイプ。
     * 
     * OpenWeatherAPI の sys.type から取得されます。
     * システム内部の値です。
     */
    protected int sysType;
    
    /**
     * システムデータのID。
     * 
     * OpenWeatherAPI の sys.id から取得されます。
     * システム内部の値です。
     */
    protected int sysId;
    
    /**
     * 国コード。
     * 
     * OpenWeatherAPI の sys.country から取得されます。
     * ISO 3166-1 alpha-2 形式の国コードです。
     * 日本の場合は "JP" です。
     */
    protected String sysCounty;
    
    /**
     * 日の出時刻（UNIXタイムスタンプ）。
     * 
     * OpenWeatherAPI の sys.sunrise から取得されます。
     * 秒単位の経過時間です。
     */
    protected int sysSunrise;
    
    /**
     * 日の入り時刻（UNIXタイムスタンプ）。
     * 
     * OpenWeatherAPI の sys.sunset から取得されます。
     * 秒単位の経過時間です。
     */
    protected int sysSunset;

    /**
     * タイムゾーン。
     * 
     * OpenWeatherAPI の timezone から取得されます。
     * UTC からの秒単位のオフセットです。
     * 日本の場合、通常 +32400 秒（UTC+9時間）です。
     */
    protected int timezone;

    /**
     * 都市/地点の一意のID。
     * 
     * OpenWeatherAPI の id から取得されます。
     * OpenWeatherAPI内での都市を特定するID です。
     */
    protected int id;

    /**
     * 都市/地点の名前。
     * 
     * OpenWeatherAPI の name から取得されます。
     * 例："Osaka"。
     */
    protected String name;
    
    /**
     * レスポンスコード。
     * 
     * OpenWeatherAPI の cod から取得されます。
     * 通常 200 はリクエスト成功を示します。
     */
    protected int cod;

    /**
     * 1時間の降水量。
     * 
     * OpenWeatherAPI の rain.1h から取得されます。
     * 単位はミリメートル（mm）です。
     * 降水がない場合は 0.0 です。
     */
    protected double rain1h;
    
    /**
     * 3時間の降水量。
     * 
     * OpenWeatherAPI の rain.3h から取得されます。
     * 単位はミリメートル（mm）です。
     * 降水がない場合は 0.0 です。
     */
    protected double rain3h;
}
