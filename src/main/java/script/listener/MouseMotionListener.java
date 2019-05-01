package script.listener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;
import script.EventStorage;
import script.action.AbstractAction;
import script.action.MouseMoveAction;

import java.awt.*;

/**
 * 用于记录鼠标的移动事件
 * Created By Dawndevil On 2019/4/29
 */
public class MouseMotionListener extends AbstractListener implements NativeMouseMotionListener {
    public MouseMotionListener(Robot robot, EventStorage storage) {
        super(robot, storage);
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        AbstractAction action = new MouseMoveAction(robot, point);
        storage.addAction(action);
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

    }
}