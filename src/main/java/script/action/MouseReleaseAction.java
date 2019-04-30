package script.action;

import script.enums.MouseButton;

import java.awt.*;

/**
 * 鼠标释放行为
 * Created By Dawndevil On 2019/4/30
 */
public class MouseReleaseAction implements CommonAction {
    private Robot robot;
    private MouseButton mouseButton;
    private Point point;

    public MouseReleaseAction(Robot robot, Point point, MouseButton mouseButton) {
        this.robot = robot;
        this.mouseButton = mouseButton;
        this.point = point;
    }

    public Robot getRobot() {
        return robot;
    }

    public MouseButton getMouseButton() {
        return mouseButton;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public void action() {
        robot.mouseRelease(mouseButton.getRobotCode());
    }

    @Override
    public String toString() {
        return "释放" + mouseButton.getName();
    }
}
