package script.action;

import script.enums.ActionEnum;

import java.awt.*;
import java.io.Serializable;

/**
 * 键盘释放行为
 * Created By Dawndevil On 2019/4/30
 */
public class KeyReleaseAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.KEY_RELEASE.getCode();

    private int keyCode;

    public KeyReleaseAction(){}

    public KeyReleaseAction(Robot robot, int keyCode) {
        super(robot);
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
