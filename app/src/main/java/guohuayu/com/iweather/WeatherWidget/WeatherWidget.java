package guohuayu.com.iweather.WeatherWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import guohuayu.com.iweather.R;
import guohuayu.com.iweather.feature.homePage.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {

    private RemoteViews mRemoteViews;
    private ComponentName mComponentName;

    private static final String STATICATION = "guohuayu.com.iweather.WeatherWidget.weatherWidget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }

        mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        mRemoteViews.setImageViewResource(R.id.widget_weatherIcon, R.drawable.qing);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //super.onReceive(context,intent);
        mRemoteViews = new RemoteViews(context.getPackageName(),R.layout.weather_widget);
        Bundle bundle = intent.getExtras();
        if(intent.getAction().equals(STATICATION)) {
            mRemoteViews.setTextViewText(R.id.widget_City, bundle.getString("city"));
            mRemoteViews.setTextViewText(R.id.widget_temp, bundle.getString("temp"));

            String weather = bundle.getString("weather");
            int imgRes = 0;
            if(weather.contains("暴雨")){
                imgRes = R.drawable.dabaoyu;
            }else if(weather.contains("霾")){
                imgRes = R.drawable.mai;
            }else if(weather.contains("雾")){
                imgRes = R.drawable.wu;
            }else if(weather.contains("小雨")){
                imgRes = R.drawable.xiaoyu;
            }else if(weather.contains("多云")){
                imgRes = R.drawable.duoyun;
            }else{
                imgRes = R.drawable.qing;
            }
            mRemoteViews.setImageViewResource(R.id.widget_weatherIcon,imgRes);

            mRemoteViews.setTextViewText(R.id.widget_wet, bundle.getString("wet"));
            mRemoteViews.setTextViewText(R.id.widget_uv, bundle.getString("uv"));
            mRemoteViews.setTextViewText(R.id.widget_wind, bundle.getString("wind"));
            mRemoteViews.setTextViewText(R.id.widget_pm, bundle.getString("pm2.5"));

        }

        Intent clickInt = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.widget_weather, pi);

        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context.getApplicationContext(),
                WeatherWidget.class), mRemoteViews);

    }
}

