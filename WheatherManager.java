public class WheatherManager extends Object {

    private String command = "";

    public void perform(){

        java.util.Scanner sc = new java.util.Scanner(System.in);

        while(!command.equals("exit")){

            System.out.print("COMMAND:"); command = sc.nextLine();

            switch (command) {
                case "weather":
                    Weather wheather = Weather.getInstance(); 
                    wheather.perform();
                    break;

                case "description":
                    System.out.println("You can get the current weather forecast for Osaka ONLY.");
                    break;

                case "help":
                    System.out.println("command:weather, description, help, exit");
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Unknown command:" + command);
                    System.out.println("Type \"help\" to view available commands.");
                    break;
            }
        }

        sc.close();
        System.exit(0);

    }
}
