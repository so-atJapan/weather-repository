/**
 * OpenWeatherAPI と通信して天気データを取得するユーティリティクラス。
 * 
 * OpenWeatherMap API を使用して、指定された都市の現在の天気情報を取得します。
 * HTTP通信でJSON形式のレスポンスを受け取り、手動でパースして
 * CurrentWeatherData オブジェクトに変換します。
 * 
 * APIキーは定数として埋め込まれています。
 * デフォルトでは大阪の天気データを取得します。
 * 気温は摂氏温度（metric）、説明は日本語（lang=ja）で取得されます。
 * 
 * 使用例：
 * <pre>
 * // デフォルト（大阪の天気を取得）
 * CurrentWeatherData data = OpenWeatherAPI.getCurrentWeather();
 * 
 * // 特定の都市の天気を取得
 * CurrentWeatherData data = OpenWeatherAPI.getCurrentWeather("Tokyo");
 * </pre>
 * 
 * @author OpenWeatherMap
 * @version 1.0
 * @see CurrentWeatherData
 * @see Weather
 */
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class OpenWeatherAPI {

    /**
     * OpenWeatherMap API のキー。
     * 
     * API リクエストの認証に使用されます。
     * このキーは ac7920c6ae71090a87e23a78af9decb7 です。
     */
    private static final String API_KEY = "ac7920c6ae71090a87e23a78af9decb7";

    /**
     * デフォルトの都市（大阪）の天気データを取得します。
     * 
     * getCurrentWeather("Osaka") と同等です。
     * 
     * @return 大阪の現在の天気データを含む CurrentWeatherData オブジェクト
     * 
     * @see #getCurrentWeather(String)
     */
    public static CurrentWeatherData getCurrentWeather(){
        return getCurrentWeather("Osaka");
    }

    /**
     * 指定された都市の現在の天気データを取得します。
     * 
     * OpenWeatherMap API にHTTPリクエストを送信して、指定された都市の
     * 現在の天気情報をJSON形式で取得します。
     * その後、JSONを手動パースして CurrentWeatherData オブジェクトに変換します。
     * 
     * API要求パラメータ：
     * - q: 都市名（例："Osaka"、"Tokyo"）
     * - appid: APIキー
     * - units: 単位設定（metric＝摂氏温度とメートル）
     * - lang: 言語設定（ja＝日本語）
     * 
     * ネットワークエラーが発生した場合、エラーメッセージを表示し、
     * 空のデータを含む CurrentWeatherData オブジェクトを返します。
     * 
     * @param city 天気情報を取得したい都市名（英語）
     *             例："Osaka"、"Tokyo"、"Kyoto"
     * 
     * @return 指定された都市の現在の天気データを含む CurrentWeatherData オブジェクト
     *         ネットワークエラーの場合、データが部分的に埋められた
     *         CurrentWeatherData オブジェクトを返します
     * 
     * @throws 例外を明示的に発生しません（内部的にキャッチして処理）
     * 
     * @example
     * <pre>
     * CurrentWeatherData osakaWeather = OpenWeatherAPI.getCurrentWeather("Osaka");
     * System.out.println(osakaWeather.mainTemp); // 気温を表示
     * System.out.println(osakaWeather.weatherMain); // 天気状態を表示
     * </pre>
     * 
     * @see #extractDouble(String, String, String)
     * @see #extractString(String, String, String)
     */
    public static CurrentWeatherData getCurrentWeather(String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY
                + "&units=metric&lang=ja";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        System.out.println(url);
        String json = "";
        try {
            json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            System.out.println(e);
        }

        // ===== JSON手動パース =====
        CurrentWeatherData data = new CurrentWeatherData();

        // coord
        data.coordLon = extractDouble(json, "\"lon\":", ",");
        data.coordLat = extractDouble(json, "\"lat\":", "}");

        // weather[0]
        data.weatherId = (int) extractDouble(json, "\"id\":", ",");
        data.weatherMain = extractString(json, "\"main\":\"", "\"");
        data.weatherDescription = extractString(json, "\"description\":\"", "\"");
        data.weatherIcon = extractString(json, "\"icon\":\"", "\"");

        // base
        data.base = extractString(json, "\"base\":\"", "\"");

        // main
        data.mainTemp = extractDouble(json, "\"temp\":", ",");
        data.mainFeelLike = extractDouble(json, "\"feels_like\":", ",");
        data.mainTempMin = extractDouble(json, "\"temp_min\":", ",");
        data.mainTempMax = extractDouble(json, "\"temp_max\":", ",");
        data.mainPressure = (int) extractDouble(json, "\"pressure\":", ",");
        data.mainHumidity = (int) extractDouble(json, "\"humidity\":", ",");
        data.mainSeaLevel = (int) extractDouble(json, "\"sea_level\":", ",");
        data.mainGrnd_Level = (int) extractDouble(json, "\"grnd_level\":", "}");

        // visibility
        data.visibility = (int) extractDouble(json, "\"visibility\":", ",");

        // wind
        data.windSpeed = extractDouble(json, "\"speed\":", ",");
        data.windDeg = (int) extractDouble(json, "\"deg\":", ",");
        data.windGust = extractDouble(json, "\"gust\":", "}");

        // clouds
        data.CloudsAll = (int) extractDouble(json, "\"all\":", "}");

        // dt
        data.dt = (int) extractDouble(json, "\"dt\":", ",");

        // sys
        data.sysType = (int) extractDouble(json, "\"type\":", ",");
        data.sysId = (int) extractDouble(json, "\"id\":", ",");
        data.sysCounty = extractString(json, "\"country\":\"", "\"");
        data.sysSunrise = (int) extractDouble(json, "\"sunrise\":", ",");
        data.sysSunset = (int) extractDouble(json, "\"sunset\":", "}");

        // timezone
        data.timezone = (int) extractDouble(json, "\"timezone\":", ",");

        // city information
        data.id = (int) extractDouble(json, "\"id\":", ",");
        data.name = extractString(json, "\"name\":\"", "\"");

        // response code
        data.cod = (int) extractDouble(json, "\"cod\":", "}");

        // rain
        data.rain1h = extractDouble(json, "\"1h\":", ",");
        data.rain3h = extractDouble(json, "\"3h\":", "}");

        return data;

    }

    /**
     * JSON文字列から数値を抽出するヘルパーメソッド。
     * 
     * 指定されたキーの直後に続く数値を JSON 文字列から抽出します。
     * 数値の終了は end パラメータで指定された文字列により判定されます。
     * 抽出後、正規表現 [^0-9+\\-.Ee] により数値以外の文字を除去します。
     * 
     * 抽出処理の流れ：
     * 1. JSON 文字列内で key を検索
     * 2. key の直後から end の位置まで部分文字列を抽出
     * 3. 非数値文字を除去
     * 4. Double として解析
     * 
     * キーが見つからない場合または解析に失敗した場合は 0.0 を返します。
     * 
     * @param json JSON文字列全体
     * @param key 検索するキー文字列（通常は "\"fieldName\":" の形式）
     * @param end 値の終了位置を示す区切り文字（通常は "," または "}"）
     * 
     * @return 抽出された数値。キーが見つからない場合は 0.0
     * 
     * @throws NumberFormatException 抽出された文字列が数値に変換できない場合
     *                               この例外は内部で処理される可能性があります
     * 
     * @example
     * <pre>
     * String json = "{\"temp\":25.5,\"humidity\":60}";
     * double temp = extractDouble(json, "\"temp\":", ",");  // 25.5 を返す
     * double humidity = extractDouble(json, "\"humidity\":", "}"); // 60.0 を返す
     * double missing = extractDouble(json, "\"missing\":", ","); // 0.0 を返す
     * </pre>
     * 
     * @see #extractString(String, String, String)
     */
    // 数値抽出
    private static double extractDouble(String json, String key, String end) {

        int start = json.indexOf(key);
        if (start == -1) {
            return 0;
        }
        start += key.length();
        int finish = json.indexOf(end, start);
        if (finish == -1) {
            finish = json.length();
        }
        String value = json.substring(start, finish).trim();
        value = value.replaceAll("[^0-9+\\-.Ee]", "");
        return Double.parseDouble(value);

    }

    // 文字列抽出
    private static String extractString(String json, String key, String end) {
        
        int start = json.indexOf(key);
        if (start == -1) {
            return "";
        }
        start += key.length();
        int finish = json.indexOf(end, start);
        return json.substring(start, finish);

    }

}