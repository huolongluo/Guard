# Guard

## 用法（具体api请参考api说明）
* androidx
```
implementation 'com.github.huolongluo:Guard:v1.0'
```
* android support
```
implementation 'com.github.huolongluo:Guard-Support:v1.0'
```

### java用法
* 注册
```
Guard.getInstance()
       .isDebug(true)
       .setPendingIntent(pendingIntent)
       .addCallback(new GuardCallback())
       ... //其他api等
       ...
       .register(this)
```
* 注销
```
Guard.getInstance().unregister(this)
```
* 重启
```
Guard.getInstance().restart(this)
```

### Kotlin用法
* 注册
```
guard {
    setPendingIntent(pendingIntent)
    setMusicId(R.raw.debug)
    isDebug(true)
    ... //其他api等
    ...
    addCallback({
       //onStop回调，可以省略
    }) { 
       //doWork回调
    }
 }
```
* 注销
```
guardUnregister()
```
* 重启
```
guardRestart()
```

## 混淆规则(proguard-rules.pro)
```
-keep class com.tqxd.guard.entity.* {*;} 
```
## Be careful:
当项目在android 8.0以上的设备，隐藏了通知栏信息，app进程一旦崩溃，则自动重启，后会出现invalid channel for service notification异常，而该异常属于系统级别的，无法直接对这种系统的异常进行捕获，假如项目里面用到一些三方异常捕获库，如Bugy之类的，不能完全保证第三方异常监控也能捕获它。 可通过调用hideNotificationAfterO(false)方法，打开通知栏信息，这样就可以查看App的存活状态了。
