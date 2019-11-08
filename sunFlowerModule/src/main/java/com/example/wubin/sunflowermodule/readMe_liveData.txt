

https://www.jianshu.com/p/2fa0aa513a32

一、定义
    LiveData是一个数据持有类。它具有以下特点：

    数据可以被观察者订阅；

    能够感知组件（Fragment、Activity、Service）的生命周期；

    只有在组件出于激活状态（STARTED、RESUMED）才会通知观察者有数据更新；

    PS: 文中提到的“组件”皆指实现了LifecycleOwner接口Fragment、Activity。


二、为什么需要LiveData
    从LiveData具有的特点，我们就能联想到它能够解决我们遇到的什么问题。LiveData具有以下优点：

    能够保证数据和UI统一
     这个和LiveData采用了观察者模式有关，LiveData是被观察者，当数据有变化时会通知观察者（UI）。
    减少内存泄漏
     这是因为LiveData能够感知到组件的生命周期，当组件处于DESTROYED状态时，观察者对象会被清除掉。
    当Activity停止时不会引起崩溃
     这是因为组件处于非激活状态时，不会收到LiveData中数据变化的通知。
    不需要额外的手动处理来响应生命周期的变化
     这一点同样是因为LiveData能够感知组件的生命周期，所以就完全不需要在代码中告诉LiveData组件的生命周期状态。
    组件和数据相关的内容能实时更新
     组件在前台的时候能够实时收到数据改变的通知，这是可以理解的。当组件从后台到前台来时，LiveData能够将最新的数据通知组件，这两点就保证了组件中和数据相关的内容能够实时更新。
    针对configuration change时，不需要额外的处理来保存数据
      我们知道，当你把数据存储在组件中时，当configuration change（比如语言、屏幕方向变化）时，组件会被recreate，然而系统并不能保证你的数据能够被恢复的。当我们采用LiveData保存数据时，因为数据和组件分离了。当组件被recreate，数据还是存在LiveData中，并不会被销毁。
    资源共享
     通过继承LiveData类，然后将该类定义成单例模式，在该类封装监听一些系统属性变化，然后通知LiveData的观察者，这个在继承LiveData中会看到具体的例子。

