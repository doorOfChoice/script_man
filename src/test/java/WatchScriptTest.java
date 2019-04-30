import org.jnativehook.NativeHookException;
import script.WatchScript;

import java.awt.*;

public class WatchScriptTest {
    public static void main(String[] args) {
        try {
            WatchScript watchScript = new WatchScript();
            watchScript.closeLogger();
            watchScript.record(5000);
            watchScript.play();
            watchScript.close();
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NativeHookException e) {
            System.out.println("请打开本地运行权限");
        }
    }
}
