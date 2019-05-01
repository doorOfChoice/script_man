package script;

import script.action.AbstractAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件存储管理器
 * Created By Dawndevil On 2019/4/29
 */
public class EventStorage {
    // 前一个动作结束的事件
    private long prevTime = System.currentTimeMillis();

    // 记录列表
    private List<CommonEvent> events = new ArrayList<>();

    /**
     * 生成事件记录但是不加入事件列表，时间也会更新
     *
     * @param action 事件行为
     * @return  事件
     */
    public CommonEvent generateAction(AbstractAction action) {
        long curTime = System.currentTimeMillis();
        long interval = curTime - prevTime;
        CommonEvent event = new CommonEvent(action, interval);
        prevTime = curTime;
        return event;
    }

    /**
     * 添加action，并且自动生成事件行为
     * @param action
     */
    public void addAction(AbstractAction action) {
        long curTime = System.currentTimeMillis();
        long interval = curTime - prevTime;
        events.add(new CommonEvent(action, interval));
        prevTime = curTime;
    }

    public List<CommonEvent> getEvents() {
        return events;
    }

    /**
     * 清除事件列表、重置结束时间
     */
    public void clear() {
        events.clear();
        prevTime = System.currentTimeMillis();
    }
}
