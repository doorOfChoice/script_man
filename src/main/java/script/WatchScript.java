package script;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import script.action.*;
import script.callback.KeyCallback;
import script.enums.ActionEnum;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * 脚本录制器
 * Created By Dawndevil on 2019/4/29
 */
public class WatchScript {
    // 记录器
    private JesusRecorder jesusRecorder;

    // 任务运行池
    private ExecutorService pool;

    // 待运行的任务
    private EventRunner task;

    private Map<String, List<CommonEvent>> loadedEvents;

    private Robot robot;

    private boolean listenMouse = true;

    private boolean listenKey = true;

    private volatile boolean shutdownRecord = false;

    /**
     * 记录运行器，负责运行各类事件
     */
    class EventRunner implements Runnable {
        private List<CommonEvent> events;

        private volatile boolean stop = false;

        EventRunner(List<CommonEvent> events) {
            this.events = events;
        }

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            for (CommonEvent commonEvent : events) {
                if (stop) {
                    break;
                }
                try {
                    Thread.sleep(commonEvent.getInterval());
                    commonEvent.getAbstractAction().action();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class EscCallback implements KeyCallback {
        private Thread thread;

        private EscCallback(Thread thread) {
            this.thread = thread;
        }

        @Override
        public boolean callback(ActionEnum type, int nativeCode, int robotCode) {
            if (type == ActionEnum.KEY_PRESS && robotCode == KeyEvent.VK_ESCAPE) {
                try {
                    shutdownRecord = true;
                    thread.interrupt();
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e) {
                    System.out.println("已经中断录制");
                }
            }
            return false;
        }
    }

    public WatchScript() throws AWTException {
        this.robot = new Robot();
        this.jesusRecorder = new JesusRecorder(this.robot);
        this.loadedEvents = new HashMap<>();
        this.pool = Executors.newCachedThreadPool();
    }

    /**
     * 关闭GlobalScreen的Logger
     */
    public void closeLogger() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setUseParentHandlers(false);
    }

    /**
     * 无时间限制的记录, 需要手动停止
     */
    public void record() throws NativeHookException {
        GlobalScreen.registerNativeHook();
        EscCallback func = new EscCallback(Thread.currentThread());
        if (listenMouse) {
            GlobalScreen.addNativeMouseMotionListener(jesusRecorder.getMouseMotionListener());
            GlobalScreen.addNativeMouseWheelListener(jesusRecorder.getMouseWheelListener());
            GlobalScreen.addNativeMouseListener(jesusRecorder.getMouseEventListener());
        }

        if (listenKey) {
            GlobalScreen.addNativeKeyListener(jesusRecorder.getKeyBoardListener(func));
        }

        while (!shutdownRecord) ;
        shutdownRecord = false;
    }

    /**
     * 录制固定的时间然后停止
     *
     * @param time 录制时常/ms
     */
    public void record(long time) throws NativeHookException {
        record();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("录制被提前中断");
        }
        stopRecord();
    }

    /**
     * 手动停止记录
     */
    public void stopRecord() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            System.out.println("手工早已停止");
        }
    }

    /**
     * 播放当前录制的脚本
     */
    public void play() {
        pool.execute(task = new EventRunner(jesusRecorder.getEvents()));
    }

    public void play(String nickname) {
        List<CommonEvent> events = loadedEvents.get(nickname);
        if (events != null) {
            pool.execute(task = new EventRunner(events));
        }
    }

    /**
     * 停止播放
     */
    public void stopPlay() {
        if (task != null) {
            task.stop();
        }
    }

    /**
     * 删除之前录制的信息
     */
    public void reset() {
        jesusRecorder.reset();
    }

    /**
     * 关闭脚本录制器
     */
    public void close() {
        if (pool != null) {
            pool.shutdown();
        }
    }

    public void saveScript(String filename) {
        jesusRecorder.saveEvents(filename);
    }


    public void loadScript(String filename) {
        loadScript(filename, filename);
    }

    public void loadScript(String filename, String nickname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONArray array = JSON.parseArray(builder.toString());
            List<CommonEvent> eventList = new ArrayList<>();
            for (Object obj : array) {
                JSONObject jobj = (JSONObject) obj;
                CommonEvent event = covertToEvent(jobj);
                event.getAbstractAction().setRobot(this.robot);
                eventList.add(event);
            }

            loadedEvents.put(nickname, eventList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CommonEvent covertToEvent(JSONObject obj) {
        int interval = obj.getInteger("interval");

        obj = obj.getJSONObject("abstractAction");

        AbstractAction action = null;

        int actionType = obj.getInteger("type");

        if (actionType == ActionEnum.KEY_PRESS.getCode()) {
            KeyPressAction t = new KeyPressAction();
            t.setKeyCode(obj.getInteger("keyCode"));
            action = t;
        } else if (actionType == ActionEnum.KEY_RELEASE.getCode()) {
            KeyReleaseAction t = new KeyReleaseAction();
            t.setKeyCode(obj.getInteger("keyCode"));
            action = t;
        } else if (actionType == ActionEnum.MOUSE_MOVE.getCode()) {
            MouseMoveAction t = new MouseMoveAction();
            Point point = new Point(
                    obj.getJSONObject("point").getInteger("x"),
                    obj.getJSONObject("point").getInteger("y")
            );
            t.setPoint(point);
            action = t;
        } else if (actionType == ActionEnum.MOUSE_PRESS.getCode()) {
            MousePressAction t1 = new MousePressAction();
            Point point1 = new Point(
                    obj.getJSONObject("point").getInteger("x"),
                    obj.getJSONObject("point").getInteger("y")
            );
            t1.setPoint(point1);

            MouseReleaseAction t2 = new MouseReleaseAction();
            JSONObject mouseReleaseObj = obj.getJSONObject("mouseReleaseAction");
            Point point2 = new Point(
                    mouseReleaseObj.getJSONObject("point").getInteger("x"),
                    mouseReleaseObj.getJSONObject("point").getInteger("y")
            );
            t2.setPoint(point2);
            t2.setRobotCode(mouseReleaseObj.getInteger("robotCode"));
            t1.setMouseReleaseAction(t2);
            t1.setRobotCode(obj.getInteger("robotCode"));
            action = t1;
        } else if (actionType == ActionEnum.MOUSE_RELEASE.getCode()) {
            MouseReleaseAction t = new MouseReleaseAction();
            Point point = new Point(
                    obj.getJSONObject("point").getInteger("x"),
                    obj.getJSONObject("point").getInteger("y")
            );
            t.setPoint(point);
            t.setRobotCode(obj.getInteger("robotCode"));
            action = t;
        } else if (actionType == ActionEnum.MOUSE_WHEEL.getCode()) {
            MouseWheelAction t = new MouseWheelAction();
            t.setAmount(obj.getInteger("amount"));
            action = t;
        }

        return new CommonEvent(action, interval);
    }

    public void setListenMouse(boolean listenMouse) {
        this.listenMouse = listenMouse;
    }

    public void setListenKey(boolean listenKey) {
        this.listenKey = listenKey;
    }
}
