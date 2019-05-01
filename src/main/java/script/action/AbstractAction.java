package script.action;

import com.alibaba.fastjson.annotation.JSONField;

import java.awt.*;
import java.io.Serializable;

/**
 * 事件行为实现接口
 * Created By Dawndevil On 2019/4/29
 */
public abstract class AbstractAction implements Serializable {
    @JSONField(serialize = false)
    protected Robot robot;

    public AbstractAction() {
    }

    public AbstractAction(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public abstract void action();
}
