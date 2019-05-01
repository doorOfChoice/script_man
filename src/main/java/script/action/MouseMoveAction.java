package script.action;

import script.enums.ActionEnum;

import java.awt.*;
import java.io.Serializable;

/**
 * 鼠标移动行为
 * Created By Dawndevil On 2019/4/29
 */
public class MouseMoveAction extends AbstractAction implements Serializable {
    private int type = ActionEnum.MOUSE_MOVE.getCode();

    private Point point;

    public MouseMoveAction() {
    }

    public MouseMoveAction(Robot robot, Point point) {
        super(robot);
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
