package script.listener;

import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import script.EventStorage;
import script.action.MouseWheelAction;

import java.awt.*;

/**
 * 鼠标滚轮监听器
 * Created By Dawndevil On 2019/4/30
 */
public class MouseWheelListener extends AbstractListener implements NativeMouseWheelListener {
    public MouseWheelListener(Robot robot, EventStorage storage) {
        super(robot, storage);
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        int amount = -1 * nativeMouseWheelEvent.getWheelRotation();

        MouseWheelAction action = new MouseWheelAction(robot, amount);
        storage.addAction(action);
    }
}
