### **组件简介**

​	   组件基于对Toast、Dialog的封装，实现Toast提示格式统一，方便使用者自定义Toast样式。兼容不同的手机系统版本，解决原有Toast框架已出现的bug。

### 组件相关配置介绍

#### 显示时长

| 属性值                | 说明                           |
| --------------------- | ------------------------------ |
| CarToast.LENGTH_SHORT | 短时间Toast显示 2s（默认时间） |
| CarToast.LENGTH_LONG  | 长时间Toast显示 3.5s           |

#### 信息排队显示方式

| 属性值                  | 说明                                 |
| ----------------------- | ------------------------------------ |
| CarToast.REPLACE_BEHIND | 排队依次显示（默认方式）             |
| CarToast.REPLACE_NOW    | 立即显示，丢弃之前未显示的消息       |
| CarToast.DISCARD        | 如果当前有消息正在显示，则丢弃不显示 |

#### 默认样式(支持修改的样式)

| 属性                    | 默认值         |
| ----------------------- | -------------- |
| 文字大小                | 14px           |
| 文字颜色                | Color.WHITE    |
| TextView  paddingStart  | 8px            |
| TextView  paddingEnd    | 8px            |
| TextView  paddingTop    | 5px            |
| TextView  paddingBottom | 5px            |
| 背景颜色                | Color.BLACK    |
| 背景透明度              | 200            |
| 显示位置（Gravity）     | Gravity.BOTTOM |
| 基于显示位置 X轴偏移量  | 0              |
| 基于显示位置 Y轴偏移量  | 100px          |
| Toast任务队列容量       | 10             |
| 背景圆角弧度            | 15px           |

### 组件使用说明

#### 添加依赖

```
maven { url 'http://172.16.0.236:8081/repository/maven-public/' }
```

```
implementation 'com.che300.car_toast:car_toast:1.0.0-20201023.103123-1'
```

#### 配置&初始化

##### 自定义样式

说明：自定义样式类需继承  BaseStyle，按需重写相关方法

```
class SelfToastStyle : BaseStyle() {
    override fun getGravity(): Int {
        return Gravity.CENTER
    }

    override fun getXOffset(): Int {
        return 0
    }

    override fun getYOffset(): Int {
        return 0
    }
}
```

##### 自定义内容拦截器

说明：自定义拦截器需继承 IToastContentIntercept

```
/**
 * Toast 内容拦截器
 * @author qinyafei
 * @since 2020/10/20 10:56
 */
class ToastContentIntercept : IToastContentIntercept {

    /**
     * @param msg CharSequence? 当前需要显示的内容
     * @return Boolean true 内容可以显示，false 丢弃
     */
    override fun intercept(msg: CharSequence?): Boolean {

        return true
    }
}

```

##### 初始化

说明：<span style="color:#ff0000">在使用之前，必须进行初始化操作；</span>style、toastContentIntercept 不赋值，使用默认参数

```
 CarToast.init(
                application = this, // 上下文对象
                style = StyleA(), // Toast样式，可以为空（使用默认样式）
                toastContentIntercept = ToastContentIntercept() //Toast 内容拦截器可以为空（使用默认拦截器）
 )
```

#### 具体使用

说明：<span style="color:#ff0000">显示方法支持在子线程中调用</span>

##### 默认显示方式显示

```
CarToast.show("内容")
```

##### 更改显示时长

```
CarToast.show("内容",CarToast.LENGTH_SHORT|CarToast.LENGTH_LONG)
```

##### 更改显示时长&排队方式

```
 CarToast.show("内容", CarToast.LENGTH_SHORT, CarToast.REPLACE_NOW)
```

##### 当前消息使用自定义样式显示

```
 CarToast.show("内容", CarToast.LENGTH_SHORT, CarToast.REPLACE_BEHIND, StyleB())
```

##### 显示自定义View

注意：自定义View支持修改的样式只有  Gravity、XOffset、YOffset！！。在使用自定义View显示的时候，应确保布局中至少包含一个TextView，如果某一个TextView指定了id = android.R.id.message 则会使用此TextView显示内容，否则将取第一个TextView用于显示内容。

```
val toastView = LayoutInflater.from(this).inflate(R.layout.toast_view, null)
CarToast.show("你好世界", SelfToastStyle(), toastView, CarToast.LENGTH_SHORT,   	CarToast.REPLACE_NOW)
```

### FAQ

待补充
