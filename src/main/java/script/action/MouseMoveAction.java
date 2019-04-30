package script.action;

import java.awt.*;

/**
 * 鼠标移动行为
 * Created By Dawndevil On 2019/4/29
 */
public class MouseMoveAction implements CommonAction {
    private Robot robot;

    private Point point;

    public MouseMoveAction(Robot robot, Point point) {
        this.robot = robot;
        this.point = point;
    }

    @Override
    public void action() {
        robot.mouseMove((int) (point.getX()), (int) (point.getY()));
    }

    @Override
    public String toString() {
        return "鼠标移动";
    }
}
