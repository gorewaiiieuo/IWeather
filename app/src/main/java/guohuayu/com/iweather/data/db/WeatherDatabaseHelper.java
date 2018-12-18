package guohuayu.com.iweather.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import guohuayu.com.iweather.data.db.entities.weatherEntities.AirQuality;
import guohuayu.com.iweather.data.db.entities.weatherEntities.LifeIndex;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherForecast;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherLive;

/**
 * Created by Administrator on 2018/12/12.
 *      weather数据库的辅助操作类
 */

public class WeatherDatabaseHelper extends OrmLiteSqliteOpenHelper{
    private static final String DATABASE_NAME = "weather,db";
    private static final int DATABASE_VERSION = 1;

    private static volatile WeatherDatabaseHelper instance;

    public WeatherDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //单例获取WeatherDatabaseHelper实例
    public static WeatherDatabaseHelper getInstance(Context context){
        context = context.getApplicationContext();
        if(instance == null){
            synchronized (WeatherDatabaseHelper.class){
                if(instance == null){
                    instance = new WeatherDatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    //创建相关数据库
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, AirQuality.class);
            TableUtils.createTableIfNotExists(connectionSource, LifeIndex.class);
            TableUtils.createTableIfNotExists(connectionSource, WeatherLive.class);
            TableUtils.createTableIfNotExists(connectionSource, WeatherForecast.class);
            TableUtils.createTableIfNotExists(connectionSource, Weather.class);

            //创建一个触发器：删除weather表后，也删除另外4个表中与weather表中cityId相同的行
            //old表示weather表
            String weatherTrigger = "create trigger trigger_delete After" +
                    "delete on weather" +
                    "for each row" +
                    "Begin" +
                    "delete from AirQuality where cityId = old.cityId" +
                    "delete from LifeIndex where cityId = old.cityId" +
                    "delete from WeatherForecast where cityId = old.cityId" +
                    "delete from WeatherLive where cityId = old.cityId" +
                    "End;";
            database.execSQL(weatherTrigger);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database, connectionSource);
    }

    @Override
    public void close() {
        super.close();
        DaoManager.clearCache();
    }

    //返回一个实现了Dao类型的子类Dao<T, ?>
    public <D extends Dao<T, ?>, T> D getWeatherDao(Class<T> clazz){
        try {
            return getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
