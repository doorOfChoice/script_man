package script;

import com.alibaba.fastjson.JSON;
import script.callback.KeyCallback;
import script.listener.KeyBoardListener;
import script.listener.MouseEventListener;
import script.listener.MouseMotionListener;
import script.listener.MouseWheelListener;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * 记录器中间层, 统一管理listener和events
 * Created By Dawndevil On 2019/4/30
 */
public class JesusRecorder {
    private MouseEventListener mouseEventListener;

    private MouseMotionListener mouseMotionListener;

    private MouseWheelListener mouseWheelListener;

    private KeyBoardListener keyBoardListener;

    private EventStorage storage = new EventStorage();

    private Robot robot;

    public JesusRecorder(Robot robot) {
        this.robot = robot;
    }

    public MouseEventListener getMouseEventListener() {
        if (mouseEventListener == null)
            mouseEventListener = new MouseEventListener(robot, storage);
        return mouseEventListener;
    }

    public MouseMotionListener getMouseMotionListener() {
        if (mouseMotionListener == null)
            mouseMotionListener = new MouseMotionListener(robot, storage);
        return mouseMotionListener;
    }

    public KeyBoardListener getKeyBoardListener() {
        return getKeyBoardListener(null);
    }

    public KeyBoardListener getKeyBoardListener(KeyCallback func) {
        if (keyBoardListener == null) {
            keyBoardListener = new KeyBoardListener(robot, storage);
            keyBoardListener.setCallback(func);
        }
        return keyBoardListener;
    }

    public MouseWheelListener getMouseWheelListener() {
        if (mouseWheelListener == null)
            mouseWheelListener = new MouseWheelListener(robot, storage);
        return mouseWheelListener;
    }

    public List<CommonEvent> getEvents() {
        return storage.getEvents();
    }

    public void saveEvents(String filename) {
        String jsonString = JSON.toJSONString(getEvents());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        storage.clear();
    }
}
