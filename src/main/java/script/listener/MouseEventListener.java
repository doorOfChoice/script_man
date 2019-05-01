
package script.listener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import script.EventStorage;
import script.action.MousePressAction;
import script.action.MouseReleaseAction;
import script.enums.MouseButton;

import java.awt.*;

/**
 * 鼠标点击行为监听器
 * Created By Dawndevil On 2019/4/29
 */
public class MouseEventListener extends AbstractListener implements NativeMouseListener {
    private MousePressAction prevPress = null;

    public MouseEventListener(Robot robot, EventStorage storage) {
        super(robot, storage);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        MouseButton btn = MouseButton.findByNativeCode(nativeMouseEvent.getButton());
        Point point = MouseInfo.getPointerInfo().getLocation();
        prevPress = new MousePressAction(robot, point, btn.getRobotCode());
        storage.addAction(prevPress);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        MouseButton btn = MouseButton.findByNativeCode(nativeMouseEvent.getButton());
        Point point = MouseInfo.getPointerInfo().getLocation();
        MouseReleaseAction action = new MouseReleaseAction(robot, point, btn.getRobotCode());
        if (prevPress != null) {
            prevPress.setMouseReleaseAction(action);
            prevPress = null;
        }
    }
}