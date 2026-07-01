/**
 * コマンド処理を管理しているクラス。
 * 
 * ユーザーから入力されたコマンドを解析し、
 * 対応する処理を実行します。
 * 利用可能なコマンド：
 * - weather: 現在の大阪の天気情報を表示
 * - description: アプリケーションを説明します
 * - help: 利用可能なコマンド一覧を表示します
 * - exit: アプリケーションを終了します
 * 
 * @author Weather Application Team
 * @version 1.0
 * @see WheatherManager
 */
public class Command extends Object {

    /**
     * 利用可能なコマンド名の配列。
     * 
     * 次のコマンドを推奨日了で埋。コマンド処理はここで定聖たれる順序で
     * 実行されます。
     * 
     * @see #COMMAND_DESCRIPTIONS
     * @see #COMMAND_ACTIONS
     */
    private static final String[] COMMAND_NAMES = {
        "weather",
        "description",
        "help",
        "exit"
    };

    /**
     * 各コマンドの説明文を推奨日了で埋。
     * 
     * COMMAND_NAMESと一対一対応しています。
     * helpコマンド実行時に、これらの説明文が表示されます。
     * 
     * @see #COMMAND_NAMES
     * @see #COMMAND_ACTIONS
     */
    private static final String[] COMMAND_DESCRIPTIONS = {
        "Show current weather.",
        "Show application descriptions.",
        "Show available commands.",
        "Exit the application."
    };

    /**
     * 各コマンドに対応する処理を推奨日了で埋。
     * 
     * Runnableインターフェースの実装体として、各コマンド実行時に
     * 対応するアクションを実行します。
     * COMMAND_NAMESと一対一対応しています。
     * 
     * @see #COMMAND_NAMES
     * @see #COMMAND_DESCRIPTIONS
     */
    private static final Runnable[] COMMAND_ACTIONS = {
        () -> Weather.getInstance().perform(),
        () -> System.out.println("You can get the current weather forecast for Osaka ONLY."),
        () -> {
            System.out.printf("%-15s%s%n", "Command", "Description");
            System.out.println("----------------------------------------------");
            for (int i = 0; i < COMMAND_NAMES.length; i++) {
                System.out.printf("%-15s%s%n", COMMAND_NAMES[i], COMMAND_DESCRIPTIONS[i]);
            }
        },
        () -> System.out.println("Exiting...")
    };

    /**
     * ユーザーから入力されたコマンド文字列を処理します。
     * 
     * 取得したコマンドをCOMMAND_NAMESと比較し、
     * 一致したコマンドが見つかった場合は対応する
     * COMMAND_ACTIONSの処理を実行します。
     * 
     * 一致しないコマンドが入力された場合は、
     * ユーザーに警戻メッセージを表示します。
     * 
     * @param command ユーザーが入力したコマンド文字列
     * @throws 例外を発生しません
     * 
     * @example
     * <pre>
     * Command.input("weather");     // weatherコマンドを実行
     * Command.input("help");        // help情報を表示
     * Command.input("invalid");     // エラーメッセージを表示
     * </pre>
     * 
     * @see #COMMAND_NAMES
     * @see #COMMAND_ACTIONS
     */
    /**
     * ユーザーから入力されたコマンド文字列を処理します。
     * 
     * 取得したコマンドをCOMMAND_NAMESと比較し、
     * 一致したコマンドが見つかった場合は対応する
     * COMMAND_ACTIONSの処理を実行します。
     * 
     * 一致しないコマンドが入力された場合は、
     * ユーザーに警戻メッセージを表示します。
     * 
     * @param command ユーザーが入力したコマンド文字列
     * @throws 例外を発生しません
     * 
     * @example
     * <pre>
     * Command.input("weather");     // weatherコマンドを実行
     * Command.input("help");        // help情報を表示
     * Command.input("invalid");     // エラーメッセージを表示
     * </pre>
     * 
     * @see #COMMAND_NAMES
     * @see #COMMAND_ACTIONS
     */
    public static void input(String command) {
        
        for (int i = 0; i < COMMAND_NAMES.length; i++) {
            if (COMMAND_NAMES[i].equals(command)) {
                COMMAND_ACTIONS[i].run();
                return;
            }
        }
        System.out.println("Unknown command: " + command);
        System.out.println("Type \"help\" to view available commands.");
        
    }

}
