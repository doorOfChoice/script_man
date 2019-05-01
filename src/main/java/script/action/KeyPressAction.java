package script.action;

import script.enums.ActionEnum;

import java.awt.*;
import java.io.Serializable;

/**
 * 键盘按下行为
 * Created By Dawndevil On 2019/4/30
 */
public class KeyPressAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.KEY_PRESS.getCode();

    private int keyCode;

    public KeyPressAction() {
    }

    public KeyPressAction(Robot robot, int keyCode) {
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
        robot.keyPress(keyCode);
    }

    @Override
    public String toString() {
        return "按下键盘";
    }
}
