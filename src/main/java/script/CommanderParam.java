package script;

import com.beust.jcommander.Parameter;

public class CommanderParam {
    @Parameter(names = {"--loaded-file", "-l"}, description = "被加载的录制文件")
    private String loadedFile = null;

    @Parameter(names = {"--out-file", "-o"}, description = "保存的文件名")
    private String outFile = null;

    @Parameter(names = {"-m"}, description = "是否监听鼠标")
    private boolean listenMouse = true;

    @Parameter(names = {"-k"}, description = "是否监听键盘")
    private boolean listenKey = true;

    @Parameter(names = {"-p"}, description = "播放脚本")
    private boolean played;

    @Parameter(names = {"-t"}, description = "录制时长/s")
    private long recordTime = 0;

    @Parameter(names = {"--help", "-h"}, help = true)
    private boolean help;

    public String getLoadedFile() {
        return loadedFile;
    }

    public void setLoadedFile(String loadedFile) {
        this.loadedFile = loadedFile;
    }

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public boolean isListenMouse() {
        return listenMouse;
    }

    public void setListenMouse(boolean listenMouse) {
        this.listenMouse = listenMouse;
    }

    public boolean isListenKey() {
        return listenKey;
    }

    public void setListenKey(boolean listenKey) {
        this.listenKey = listenKey;
    }

    public long getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(long recordTime) {
        this.recordTime = recordTime;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    @Override
    public String toString() {
        return "CommanderParam{" +
                "loadedFile='" + loadedFile + '\'' +
                ", outFile='" + outFile + '\'' +
                ", listenMouse=" + listenMouse +
                ", listenKey=" + listenKey +
                ", played=" + played +
                ", recordTime=" + recordTime +
                ", help=" + help +
                '}';
    }
}
