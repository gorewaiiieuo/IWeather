package guohuayu.com.iweather.data.db.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/12/14.
 */
@DatabaseTable(tableName = "City")
public class City {
    public static final String ID_FIELD_NAME = "_id";
    public static final String ROOT_FIELD_NAME = "root";
    public static final String PARENT_FIELD_NAME = "parent";
    public static final String CITY_NAME_FIELD_NAME = "name";
    public static final String CITY_NAME_EN_FIELD_NAME = "pinyin";
    public static final String LON_FIELD_NAME = "x";
    public static final String LAT_FIELD_NAME = "y";
    public static final String CITY_ID_FIELD_NAME = "posID";

    //指定id为自增长主键 由数据库系统根据定义自动赋值
    @DatabaseField(columnName = ID_FIELD_NAME, generatedId = true)
    private int id;
    @DatabaseField(columnName = ROOT_FIELD_NAME)
    private String root;

    @DatabaseField(columnName = PARENT_FIELD_NAME)
    private String parent;

    @DatabaseField(columnName = CITY_NAME_FIELD_NAME)
    private String cityName;

    @DatabaseField(columnName = CITY_NAME_EN_FIELD_NAME)
    private String CityNameEng;

    @DatabaseField(columnName = LON_FIELD_NAME)
    private String lon;

    @DatabaseField(columnName = LAT_FIELD_NAME)
    private String lat;

    @DatabaseField(columnName = CITY_ID_FIELD_NAME)
    private String cityID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityNameEng() {
        return CityNameEng;
    }

    public void setCityNameEng(String cityNameEng) {
        CityNameEng = cityNameEng;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", root='" + root + '\'' +
                ", parent='" + parent + '\'' +
                ", cityName='" + cityName + '\'' +
                ", CityNameEng='" + CityNameEng + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", cityID='" + cityID + '\'' +
                '}';
    }
}
