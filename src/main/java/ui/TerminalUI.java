package ui;

import com.beust.jcommander.JCommander;
import script.CommanderParam;
import script.WatchScript;

public class TerminalUI {
    public static void main(String[] args) {
        CommanderParam params = new CommanderParam();
        JCommander commander = JCommander.newBuilder()
                .addObject(params)
                .build();
        commander.parse(args);
        try {
            if(params.isHelp()) {
                commander.usage();
                return;
            }
            WatchScript script = new WatchScript();
            script.setListenKey(params.isListenKey());
            script.setListenMouse(params.isListenMouse());
            script.closeLogger();
            if (params.getLoadedFile() != null) {
                System.out.println("开始加载文件");
                script.loadScript(params.getLoadedFile(), "main");
                System.out.println("开始播放");
                script.play("main");
            } else {
                System.out.println("开始录制, 录制时间" + params.getRecordTime() + "s");
                if(params.getRecordTime() > 0) {
                    script.record(params.getRecordTime() * 1000);
                } else {
                    script.record();
                }
                script.stopRecord();
                if(params.isPlayed()) {
                    System.out.println("开始播放");
                    script.play();
                }
                if (params.getOutFile() != null) {
                    System.out.println("开始保存到: " + params.getOutFile());
                    script.saveScript(params.getOutFile());
                }
            }
            script.close();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
