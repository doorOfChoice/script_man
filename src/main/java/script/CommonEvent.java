package script;

import script.action.CommonAction;

/**
 * 事件类
 * 记录器会根据时间间隔和事件行为来复现操作
 * Created By Dawndevil On 2019/4/29
 */
public class CommonEvent {
    // 距离上次操作间隔时间
    private long interval;

    // 事件行为
    private CommonAction commonAction;

    public CommonEvent(CommonAction commonAction, long interval) {
        this.commonAction = commonAction;
        this.interval = interval;
    }

    public CommonAction getAction() {
        return commonAction;
    }

    public long getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "CommonEvent{" +
                "interval=" + interval +
                ", commonAction=" + commonAction +
                '}';
    }
}