/**
 * アプリケーションの主要な処理を管理するクラス。
 * 
 * ユーザーからのコマンド入力を受け付け、コマンドを解析して
 * 対応する処理を実行する一連のフローを管理します。
 * exitコマンドが入力されるまでループを続けます。
 * 
 * @author Weather Application Team
 * @version 1.0
 * @see Main
 * @see Command
 */
public class WheatherManager extends Object {

    /**
     * ユーザー入力を保持するフィールド。
     * 
     * perform()メソッド内で繰り返し使用され、
     * ユーザーが入力したコマンド文字列が格納されます。
     * 初期値は空文字列です。
     */
    private String command = "";

    /**
     * アプリケーションのメイン処理を実行します。
     * 
     * Scanner を使用してユーザーからコマンド入力を受け付け、
     * exitコマンドが入力されるまでループを続けます。
     * 各コマンドは Command.input() メソッドに渡されて処理されます。
     * ループ終了時には、Scannerをクローズしてプログラムを終了します。
     * 
     * 処理フロー：
     * 1. Scannerを初期化
     * 2. "exit"が入力されるまでループ
     *    - "COMMAND:"プロンプトを表示
     *    - ユーザー入力を取得
     *    - Command.input()でコマンドを処理
     * 3. ループ終了後、Scannerをクローズして System.exit(0) で終了
     * 
     * @throws IOException Scannerの操作時に例外が発生する可能性
     * 
     * @example
     * <pre>
     * WheatherManager manager = new WheatherManager();
     * manager.perform();  // プログラムを開始
     * </pre>
     * 
     * @see Command#input(String)
     */
    /**
     * アプリケーションのメイン処理を実行します。
     * 
     * Scanner を使用してユーザーからコマンド入力を受け付け、
     * exitコマンドが入力されるまでループを続けます。
     * 各コマンドは Command.input() メソッドに渡されて処理されます。
     * ループ終了時には、Scannerをクローズしてプログラムを終了します。
     * 
     * 処理フロー：
     * 1. Scannerを初期化
     * 2. "exit"が入力されるまでループ
     *    - "COMMAND:"プロンプトを表示
     *    - ユーザー入力を取得
     *    - Command.input()でコマンドを処理
     * 3. ループ終了後、Scannerをクローズして System.exit(0) で終了
     * 
     * @throws IOException Scannerの操作時に例外が発生する可能性
     * 
     * @example
     * <pre>
     * WheatherManager manager = new WheatherManager();
     * manager.perform();  // プログラムを開始
     * </pre>
     * 
     * @see Command#input(String)
     */
    public void perform() {

        java.util.Scanner sc = new java.util.Scanner(System.in);

        while (!command.equals("exit")) {
            System.out.print("COMMAND:");
            command = sc.nextLine();
            Command.input(command);
        }

        sc.close();
        System.exit(0);

    }
}
