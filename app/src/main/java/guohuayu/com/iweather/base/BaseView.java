package guohuayu.com.iweather.base;

/**
 * 为mvp设计模式中的view提供父类，项目中所有view必须实现此接口（主要是fragment和自定义的view）
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}
