# script_man

脚本录制器


## 使用方法

```bash
  Options:
    --help, -h

    --loaded-file, -l
      被加载的录制文件
      
    --out-file, -o
      保存的文件名
    -k
      是否监听键盘
      Default: true
    -m
      是否监听鼠标
      Default: true
    -p
      播放脚本
      Default: false
    -t
      录制时长/s
      Default: 0

      
# 录制并且播放
java -jar scriptman.jar -p

# 只监听鼠标
java -jar scriptman.jar -k -p
```

保存文件请你smf为后缀，录制过程中可按Esc提前结束录制

## 改进计划

[] 循环调用(目前可以写bash)
[] Key一直监听，否则可能出现快捷键不能用