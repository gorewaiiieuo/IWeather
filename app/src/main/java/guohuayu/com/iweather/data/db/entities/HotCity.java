package guohuayu.com.iweather.data.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/12/14.
 */

@DatabaseTable(tableName = "HotCity")
public class HotCity {
    public static final String ID_FIELD_NAME = "_id";
    public static final String CITY_NAME_FIELD_NAME = "name";
    public static final String POS_ID_FIELD_NAME = "posID";

    //指定id为自增长主键 由数据库系统根据定义自动赋值
    @DatabaseField(columnName = ID_FIELD_NAME, generatedId = true)
    private int id;

    @DatabaseField(columnName = POS_ID_FIELD_NAME)
    private String CityID;

    @DatabaseField(columnName = CITY_NAME_FIELD_NAME)
    private String cityName;



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }


}
