package script;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import script.listener.JesusRecorder;

import java.awt.*;
import java.util.List;
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
                    commonEvent.getAction().action();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public WatchScript() throws AWTException {
        jesusRecorder = new JesusRecorder(new Robot());

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
        GlobalScreen.addNativeMouseMotionListener(jesusRecorder.getMouseMotionListener());
        GlobalScreen.addNativeMouseWheelListener(jesusRecorder.getMouseWheelListener());
        GlobalScreen.addNativeMouseListener(jesusRecorder.getMouseEventListener());
        GlobalScreen.addNativeKeyListener(jesusRecorder.getKeyBoardListener());
    }

    /**
     * 录制固定的时间然后停止
     *
     * @param time 录制时常/ms
     * @throws InterruptedException
     */
    public void record(long time) throws InterruptedException, NativeHookException {
        record();
        Thread.sleep(time);
        stopRecord();
    }

    /**
     * 手动停止记录
     */
    public void stopRecord() {
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放当前录制的脚本
     */
    public void play() {
        if (pool == null || pool.isShutdown()) {
            pool = Executors.newCachedThreadPool();
        }
        pool.execute(task = new EventRunner(jesusRecorder.getEvents()));
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

}
