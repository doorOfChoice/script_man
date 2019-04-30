package script.enums;

import java.awt.event.InputEvent;

/**
 * 鼠标键枚举类
 * Created By Dawndevil On 2019/4/29
 */
public enum MouseButton {
    LEFT(1, InputEvent.BUTTON1_MASK, "左键"),
    RIGHT(2, InputEvent.BUTTON3_MASK, "右键");

    // jnativehook code
    private int nativeCode;

    // java原生code
    private int robotCode;

    // 名字
    private String name;

    MouseButton(int nativeCode, int robotCode, String name) {
        this.nativeCode = nativeCode;
        this.robotCode = robotCode;
        this.name = name;
    }

    public static MouseButton findByNativeCode(int code) {
        for(MouseButton mouseButton: MouseButton.values()) {
            if(code == mouseButton.nativeCode) {
                return mouseButton;
            }
        }
        return null;
    }

    public static MouseButton findByRobotCode(int code) {
        for(MouseButton mouseButton: MouseButton.values()) {
            if(code == mouseButton.robotCode) {
                return mouseButton;
            }
        }
        return null;
    }

    public int getNativeCode() {
        return nativeCode;
    }

    public int getRobotCode() {
        return robotCode;
    }

    public String getName() {
        return name;
    }
}
