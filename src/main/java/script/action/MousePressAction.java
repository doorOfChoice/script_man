package script.action;

import script.enums.MouseButton;

import java.awt.*;

/**
 * 鼠标按下行为
 * Created By Dawndevil On 2019/4/29
 */
public class MousePressAction implements CommonAction {
    private Robot robot;
    private MouseButton mouseButton;
    private Point point;
    private MouseReleaseAction mouseReleaseAction;
    private long interval = 0;

    public MousePressAction(Robot robot, Point point, MouseButton mouseButton) {
        this.robot = robot;
        this.mouseButton = mouseButton;
        this.point = point;
    }

    public void setReleaseAction(MouseReleaseAction mouseReleaseAction, long interval) {
        this.mouseReleaseAction = mouseReleaseAction;
        this.interval = interval;
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
        robot.mousePress(mouseButton.getRobotCode());
        if (mouseReleaseAction != null) {
            if (!point.equals(mouseReleaseAction.getPoint())) {
                robot.delay(10);
                robot.mouseMove(
                        (int) (mouseReleaseAction.getPoint().getX()),
                        (int) (mouseReleaseAction.getPoint().getY())
                );
                robot.delay(10);
            }
            robot.mouseRelease(mouseReleaseAction.getMouseButton().getRobotCode());
        }
    }

    @Override
    public String toString() {
        return "按下" + mouseButton.getName();
    }
}
