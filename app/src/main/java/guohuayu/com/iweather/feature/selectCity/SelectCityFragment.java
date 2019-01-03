package guohuayu.com.iweather.feature.selectCity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baronzhang.android.library.view.DividerItemDecoration;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseFragment;
import guohuayu.com.iweather.data.db.entities.City;
import guohuayu.com.iweather.data.preference.PreferenceHelper;
import guohuayu.com.iweather.data.preference.WeatherSettings;

/*
* 未实现
* 本fragment将显示在SelectCityActivity中
* */

public class SelectCityFragment extends BaseFragment implements SelectCityContract.View {


    public List<City> cities;
    public CityListAdapter cityListAdapter;

    @BindView(R.id.city_list)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private SelectCityContract.Presenter presenter;

    public SelectCityFragment() {
    }

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_city, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        cities = new ArrayList<>();
        cityListAdapter = new CityListAdapter(cities);
        cityListAdapter.setOnItemClickListener((parent, view, position, id) -> {
            try {
                City selectedCity = cityListAdapter.mFilterData.get(position);
                PreferenceHelper.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, selectedCity.getCityID() + "");
                Toast.makeText(this.getActivity(), selectedCity.getCityName(), Toast.LENGTH_LONG).show();
                getActivity().finish();
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        });
        recyclerView.setAdapter(cityListAdapter);
        presenter.subscribe();
        return rootView;
    }

    @Override
    public void setPresenter(SelectCityContract.Presenter presenter) {
            this.presenter = presenter;
    }

    @Override
    public void displayCities(List<City> cities) {
        this.cities.addAll(cities);
        cityListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.unSubscribe();
    }

}
