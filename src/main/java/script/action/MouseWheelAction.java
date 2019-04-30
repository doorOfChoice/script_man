package script.action;

import java.awt.*;

/**
 * 鼠标滚轮行为
 * Created By Dawndevil On 2019/4/30
 */
public class MouseWheelAction implements CommonAction {
    private Robot robot;

    private int amount;

    public MouseWheelAction(Robot robot, int amount) {
        this.robot = robot;
        this.amount = amount;
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
