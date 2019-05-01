package script.action;

import script.enums.ActionEnum;

import java.awt.*;
import java.io.Serializable;

/**
 * 鼠标滚轮行为
 * Created By Dawndevil On 2019/4/30
 */
public class MouseWheelAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.MOUSE_WHEEL.getCode();
    private int amount;

    public MouseWheelAction() {
    }

    public MouseWheelAction(Robot robot, int amount) {
        super(robot);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void action() {
        robot.mouseWheel(amount);
    }

    @Override
    public String toString() {
        return "滚轮上线";
    }
}
