package script.callback;

import script.enums.ActionEnum;

public interface KeyCallback {
    /**
     * 按键事件的回调
     *
     * @param type       ActionEnum元素
     * @param nativeCode 本地键盘code
     * @param robotCode  java键盘code
     */
    boolean callback(ActionEnum type, int nativeCode, int robotCode);
}
