/**
 * アプリケーションのエントリーポイント。
 * 
 * 大阪の天気情報を取得・表示するアプリケーションを起動するメインクラスです。
 * WeatherManagerを生成して、ユーザー入力の処理を開始します。
 * 
 * @author Weather Application Team
 * @version 1.0
 */
public class Main {

    /**
     * プログラムの実行開始ポイント。
     * 
     * WeatherManagerのインスタンスを作成し、perform()メソッドを呼び出して
     * アプリケーション本体を起動します。
     * 
     * @param args コマンドライン引数（未使用）
     * @since 1.0
     */
    public static void main(String[] args) {

        WheatherManager wheatherManager = new WheatherManager();
        wheatherManager.perform();
        
    }
}
