package guohuayu.com.iweather.feature.selectCity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseFragment;
import guohuayu.com.iweather.data.db.entities.City;

/*
* 未实现
* 本fragment将显示在SelectCityActivity中
* */

public class SelectCityFragment extends BaseFragment implements SelectCityContract.View {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_city, container, false);
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {

    }

    @Override
    public void displayCities(List<City> cities) {

    }
}
