package script.action;

import script.enums.ActionEnum;

import java.awt.*;
import java.io.Serializable;

/**
 * 鼠标按下行为
 * Created By Dawndevil On 2019/4/29
 */
public class MousePressAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.MOUSE_PRESS.getCode();
    private int robotCode;
    private Point point;
    private MouseReleaseAction mouseReleaseAction;

    public MousePressAction() {
    }

    public MousePressAction(Robot robot, Point point, int robotCode) {
        super(robot);
        this.robotCode = robotCode;
        this.point = point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(int robotCode) {
        this.robotCode = robotCode;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public MouseReleaseAction getMouseReleaseAction() {
        return mouseReleaseAction;
    }

    public void setMouseReleaseAction(MouseReleaseAction mouseReleaseAction) {
        this.mouseReleaseAction = mouseReleaseAction;
    }

    @Override
    public void action() {
        robot.mousePress(robotCode);
        if (mouseReleaseAction != null) {
            if (!point.equals(mouseReleaseAction.getPoint())) {
                robot.delay(10);
                robot.mouseMove(
                        (int) (mouseReleaseAction.getPoint().getX()),
                        (int) (mouseReleaseAction.getPoint().getY())
                );
                robot.delay(10);
            }
            robot.mouseRelease(mouseReleaseAction.getRobotCode());
        }
    }

    @Override
    public String toString() {
        return "按下";
    }
}
