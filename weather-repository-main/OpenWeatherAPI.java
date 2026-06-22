import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class OpenWeatherAPI {

    private static final String API_KEY = "ac7920c6ae71090a87e23a78af9decb7";

    public static CurrentWeatherData getCurrentWeather(){
        return getCurrentWeather("Osaka");
    }

    public static CurrentWeatherData getCurrentWeather (String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city + "&appid=" + API_KEY + "&units=metric&lang=ja";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        String json = "";
        try {
            json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(json);

        // ===== JSON手動パース =====
        CurrentWeatherData data = new CurrentWeatherData();

        // coord
        data.coordLon = extractDouble(json, "\"lon\":", ",");
        data.coordLat = extractDouble(json, "\"lat\":", "}");

        // weather[0]
        data.weatherId = (int)extractDouble(json, "\"id\":", ",");
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
        data.mainPressure = (int)extractDouble(json, "\"pressure\":", ",");
        data.mainHumidity = (int)extractDouble(json, "\"humidity\":", ",");
        data.mainSeaLevel = (int)extractDouble(json, "\"sea_level\":", ",");
        data.mainGrnd_Level = (int)extractDouble(json, "\"grnd_level\":", "}");

        // visibility
        data.visibility = (int)extractDouble(json, "\"visibility\":", ",");

        // wind
        data.windSpeed = extractDouble(json, "\"speed\":", ",");
        data.windDeg = (int)extractDouble(json, "\"deg\":", ",");
        data.windGust = extractDouble(json, "\"gust\":", "}");

        // clouds
        data.CloudsAll = (int)extractDouble(json, "\"all\":", "}");

        // dt
        data.dt = (int)extractDouble(json, "\"dt\":", ",");

        // sys
        data.sysType = (int)extractDouble(json, "\"type\":", ",");
        data.sysId = (int)extractDouble(json, "\"id\":", ",");
        data.sysCounty = extractString(json, "\"country\":\"", "\"");
        data.sysSunrise = (int)extractDouble(json, "\"sunrise\":", ",");
        data.sysSunset = (int)extractDouble(json, "\"sunset\":", "}");

        // timezone
        data.timezone = (int)extractDouble(json, "\"timezone\":", ",");

        // city information
        data.id = (int)extractDouble(json, "\"id\":", ",");
        data.name = extractString(json, "\"name\":\"", "\"");

        // response code
        data.cod = (int)extractDouble(json, "\"cod\":", "}");



        return data;
    }

    // 数値抽出
    private static double extractDouble(String json, String key, String end) {
        int start = json.indexOf(key);
        if (start == -1) return 0;

        start += key.length();
        int finish = json.indexOf(end, start);

        return Double.parseDouble(json.substring(start, finish));
    }

    // 文字列抽出
    private static String extractString(String json, String key, String end) {
        int start = json.indexOf(key);
        if (start == -1) return "";

        start += key.length();
        int finish = json.indexOf(end, start);

        return json.substring(start, finish);
    }
}