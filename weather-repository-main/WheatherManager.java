public class WheatherManager extends Object {

    public void perform(){
        CurrentWeather wheather = CurrentWeather.getInstance(); 

        System.out.println(wheather.toString());
    }
}
