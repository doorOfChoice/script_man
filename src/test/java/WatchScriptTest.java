import script.WatchScript;

public class WatchScriptTest {
    public static void main(String[] args) {
        try {
            WatchScript watchScript = new WatchScript();
            watchScript.closeLogger();
//            watchScript.record(5000);
//            watchScript.saveScript("script.smf");
//            watchScript.play();
            watchScript.loadScript("script.smf", "one");
            watchScript.play("one");
            watchScript.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
