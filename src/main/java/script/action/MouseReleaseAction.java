package script.action;

import script.enums.ActionEnum;
import script.enums.MouseButton;

import java.awt.*;
import java.io.Serializable;

/**
 * 鼠标释放行为
 * Created By Dawndevil On 2019/4/30
 */
public class MouseReleaseAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.MOUSE_RELEASE.getCode();
    private int robotCode;
    private Point point;

    public MouseReleaseAction() {
    }

    public MouseReleaseAction(Robot robot, Point point, int robotCode) {
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

    @Override
    public void action() {
        robot.mouseRelease(robotCode);
    }

    @Override
    public String toString() {
        return "释放";
    }
}
