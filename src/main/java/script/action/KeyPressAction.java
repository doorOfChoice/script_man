package script.action;

import java.awt.*;

/**
 * 键盘按下行为
 * Created By Dawndevil On 2019/4/30
 */
public class KeyPressAction implements CommonAction {

    private Robot robot;

    private int keyCode;

    public KeyPressAction(Robot robot, int keyCode) {
        this.robot = robot;
        this.keyCode = keyCode;
    }

    @Override
    public void action() {
        robot.keyPress(keyCode);
    }

    @Override
    public String toString() {
        return "按下键盘";
    }
}
