package script.listener;

import script.EventStorage;

import java.awt.*;

/**
 * 抽象监听类
 * Created By Dawndevil On 2019/4/30
 */
public class AbstractListener {
    protected Robot robot;

    protected EventStorage storage;

    public AbstractListener() {

    }

    public AbstractListener(Robot robot, EventStorage storage) {
        this.robot = robot;
        this.storage = storage;
    }


}
