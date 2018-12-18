package guohuayu.com.iweather.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import guohuayu.com.iweather.WeatherApplication;
import guohuayu.com.iweather.di.module.ApplicationModule;

/* 格式要求 对应ApplicationModule中两个provide
* 我们再把上例中的注入过程梳理一下：

1、首先定义一个类User 并在其构造函数用@Inject标注，表示告诉Dagger2这是我的构造函数，如果有地方要用到我，就用该构造函数对我实例化；

2、创建一个@Component标注的注入器接口，并在注入器中使用 void inject(WelcomeActivity WelcomeActivity);来表明哪里要用到注入器；

这里表示MainActivity中要用到该注入器

3、在MainActivity中对注入器进行初始化DaggerActivityComponent.builder().build().inject(this); 初始化后该注入器就可以正常使用了；

4、在MainActivity中对需要注入的类  User用@Inject进行标注，表示该类需要被注入，即实例化；
* */

/*
* 注入器接口 APP级别component对象
* 负责连接applicationModule和Application
* */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    WeatherApplication getApplication();

    Context getContext();
}
