package guohuayu.com.iweather.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import guohuayu.com.iweather.R;
import guohuayu.com.iweather.WeatherApplication;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/12/12.
 */

public final class CityDatabaseHelper extends OrmLiteSqliteOpenHelper{
    private static final String TAG = "CityDatabaseHelper";

    private static final String DATABASE_NAME = "city.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile CityDatabaseHelper instance;

    public CityDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //由于城市数据库是由外部资源res.raw导入的，故不需要创建执行创建表的操作
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /*
    * 单例获取openhelper实例
    * */

    public static CityDatabaseHelper getInstance(Context context){
        context = context.getApplicationContext();
        if(context == null){
            synchronized (CityDatabaseHelper.class){
                if(instance == null){
                    instance = new CityDatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    //清除缓存
    @Override
    public void close() {
        super.close();
        DaoManager.clearCache();
    }

    public <D extends Dao<T, ?>, T> D getCityDao(Class<T> clazz){
        try{
            return getDao(clazz);
        }catch (SQLException e){
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /*
    *  导入城市数据库
    * */
    public static void importCityDB(){
        //判断城市数据库文件是否存在
        File file = new File(WeatherApplication.getInstance().getDatabasePath(DATABASE_NAME).getAbsolutePath());
        if(!file.exists()){
            File dbfile = WeatherApplication.getInstance().getDatabasePath(DATABASE_NAME);
            try {
                if(!dbfile.getParentFile().exists()){
                    dbfile.getParentFile().mkdir();
                }
                if(!dbfile.exists()){
                    dbfile.createNewFile();
                }
                //加载城市数据库
                InputStream is = WeatherApplication.getInstance().getResources().openRawResource(R.raw.city);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);  //is,read
                fos.write(buffer);//fos.write
                is.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
