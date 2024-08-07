
class TV{
    public void turnOn(){System.out.println("TV turned on...");}
}

class SoundBar{
    public void on(){System.out.println("The SoundBar is on now...");}

    public void changeMod(String mod){
        if(mod.equals("Cinema") || mod.equals("Tv Show"))
            System.out.println("The SoundBar mod changed to " + mod);
        else
            System.out.println("Wrong mod...");
    }
}

class StreamingDevice{
    public void startDevice(){System.out.println("Device is ready to stream...");}

    public void chooseApp(String app){
        if(app.equals("Netflix") || app.equals("Exxen") || app.equals("Amazon Prime"))
            System.out.println("Streaming from " + app);
        else
            System.out.println("Wrong app name...");
    }
}
class Facade {
    public static void main(String[] args) {
        String desiredMod = "Cinema";
        String desiredApp = "Netflix";

        FacadeClass.getFacade().execute(desiredMod, desiredApp);
    }
}

class FacadeClass {
    private StreamingDevice streamingDevice;
    private SoundBar soundBar;
    private TV tv;

    private static FacadeClass uniqueFacade = null;

    private FacadeClass() {
        this.streamingDevice = new StreamingDevice();
        this.soundBar = new SoundBar();
        this.tv = new TV();
    }

    public static FacadeClass getFacade() {
        if (uniqueFacade == null) {
            System.out.println("Facade creating..");
            uniqueFacade = new FacadeClass();
        }
        return uniqueFacade;
    }

    void execute(String mod, String app) {
        tv.turnOn();
        soundBar.on();
        streamingDevice.startDevice();
        soundBar.changeMod(mod);
        streamingDevice.chooseApp(app);
    }
}
