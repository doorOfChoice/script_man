package script.action;

import java.awt.*;

/**
 * 键盘释放行为
 * Created By Dawndevil On 2019/4/30
 */
public class KeyReleaseAction implements CommonAction {
    private Robot robot;

    private int keyCode;

    public KeyReleaseAction(Robot robot, int keyCode) {
        this.robot = robot;
        this.keyCode = keyCode;
    }

    @Override
    public void action() {
        robot.keyRelease(keyCode);
    }

    @Override
    public String toString() {
        return "松开键盘";
    }
}
