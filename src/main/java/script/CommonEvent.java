package script;

import script.action.AbstractAction;

import java.io.Serializable;

/**
 * 事件类
 * 记录器会根据时间间隔和事件行为来复现操作
 * Created By Dawndevil On 2019/4/29
 */
public class CommonEvent implements Serializable {
    // 距离上次操作间隔时间
    private long interval;

    // 事件行为
    private AbstractAction abstractAction;

    public CommonEvent(){}

    public CommonEvent(AbstractAction abstractAction, long interval) {
        this.abstractAction = abstractAction;
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public AbstractAction getAbstractAction() {
        return abstractAction;
    }

    public void setAbstractAction(AbstractAction abstractAction) {
        this.abstractAction = abstractAction;
    }

    @Override
    public String toString() {
        return "CommonEvent{" +
                "interval=" + interval +
                ", abstractAction=" + abstractAction +
                '}';
    }
}