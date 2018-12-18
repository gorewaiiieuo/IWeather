package guohuayu.com.iweather.base;

/**
 * 为mvp设计模式提供presenter的父类，所有presenter必须实现此接口
 */

public interface BasePresenter {
    void subscribe(); //订阅

    void unSubscribe();//解除订阅
}
