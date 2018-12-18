package guohuayu.com.iweather.data.http;

import com.baronzhang.retrofit2.converter.FastJsonConverterFactory;

import guohuayu.com.iweather.BuildConfig;
import guohuayu.com.iweather.data.http.service.EnvironmentCloudWeatherService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2018/12/13.
 */

public class ApiClient {
    public static EnvironmentCloudWeatherService environmentCloudWeatherService;

    public static void init(){
        String ApiHost = ApiConstants.ENVICLOUD_API;
        environmentCloudWeatherService = initWeatherService(ApiHost, EnvironmentCloudWeatherService.class);
    }

    private static <T> T initWeatherService(String baseUrl, Class<T> clazz){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())//为retrofit添加数据转换工厂
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//为retrofit添加rxjava的适配工厂，以将接口中定义的observable创建
                .client(client)
                .build();

        return retrofit.create(clazz);
    }
}
