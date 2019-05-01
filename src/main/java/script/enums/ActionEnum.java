package script.enums;

/**
 * 行为类型标识
 * Created By Dawndevil On 2019/5/1
 */
public enum ActionEnum {
    KEY_PRESS(0),
    KEY_RELEASE(1),
    MOUSE_MOVE(2),
    MOUSE_PRESS(3),
    MOUSE_RELEASE(4),
    MOUSE_WHEEL(5);

    private int code;

    ActionEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
