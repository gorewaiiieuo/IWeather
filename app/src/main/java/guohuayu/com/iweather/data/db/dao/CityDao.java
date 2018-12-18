package guohuayu.com.iweather.data.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import guohuayu.com.iweather.data.db.CityDatabaseHelper;
import guohuayu.com.iweather.data.db.entities.City;
import guohuayu.com.iweather.data.db.entities.HotCity;

/**
 * Created by Administrator on 2018/12/14.
 */

public class CityDao {
    private Dao<City, Integer> cityDaoOperation;
    private Dao<HotCity, Integer> hotCityDaoOperation;

    @Inject
    CityDao(Context context){
        this.cityDaoOperation = CityDatabaseHelper.getInstance(context).getCityDao(City.class);
        this.hotCityDaoOperation = CityDatabaseHelper.getInstance(context).getCityDao(HotCity.class);
    }

    /*
    * 查询city表中所有城市
    * */
    public List<City> queryCityList(){
        try {
            return cityDaoOperation.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据城市id查询城市信息
     * */
    public City queryCityById(String cityId) throws SQLException {
        QueryBuilder<City, Integer> builder = cityDaoOperation.queryBuilder();
        builder.where().eq(City.CITY_ID_FIELD_NAME, cityId);

        return builder.queryForFirst();
    }

    /*
    * 查询热门城市
    * */

    public List<HotCity> queryHotCityList(){
        try {
            return hotCityDaoOperation.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
