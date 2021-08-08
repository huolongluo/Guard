# Guard
## Be careful:
项目里如果使用了Thread.UncaughtExceptionHandler或者第三方异常捕获库，比如友盟，bugly等，Guard请在Thread.UncaughtExceptionHandler或者第三方异常捕获库，比如友盟，bugly等之后注册使用，并且建议在Application里注册使用。 为什么要这样操作？因为如果android 8.0以上设备隐藏了通知栏信息，当您的app崩溃重启后会出现invalid channel for service notification异常，而该异常属于系统级别的，没法捕获，所以Guard对该异常进行了杀死app的操作，但是并不能保证第三方异常监控还是能捕获它。 如果第三方后台还是有该异常信息，你又觉得该异常影响你的app崩溃率，请调用hideNotificationAfterO(false)方法，打开通知栏信息。

## 用法（具体api请参考api说明）
* androidx
```
implementation 'com.github.huolongluo:Guard:v1.0'
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
