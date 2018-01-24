package com.example.coolweather;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coolweather.db.City;
import com.example.coolweather.db.Country;
import com.example.coolweather.db.Province;
import com.example.coolweather.util.HttpUtil;
import com.example.coolweather.util.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by xuyue on 2018/1/24.
 */
//用于遍历省市县数据的碎片，由于遍历省市县的功能在后续会复用，所以写在碎片里可以方便在布局文件里直接引用碎片
public class ChooseAreaFragment extends Fragment {

    //定义一些用于判断当前数据类型（省、市、县）的常量
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTRY = 2;

    private int currentLevel;//记录当前数据的级别

    private ProgressDialog progressDialog;//显示向服务器查询数据的进度

    //布局中的相关控件
    private TextView titleText;//标题
    private Button backButton;//标题栏的返回按钮
    private ListView listView;//显示数据的列表

    //填充列表所需的数据
    private ArrayAdapter<String> adapter;//适配器
    private List<String> dataList = new ArrayList<>();
    private List<Province> provinceList;//省列表
    private List<City> cityList;//市列表
    private List<Country> countryList;//县列表

    //记录中的省份和城市
    private Province selectedProvince;
    private City selectedCity;

    //初始化相关控件，并给列表设置适配器
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //给列表元素添加监听事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //根据当前列表的级别进行相应的数据更新
                if (currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
                    //根据选中的省份查找对应的市数据信息，更新列表
                    queryCities();
                }else if (currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(position);
                    //根据选中的城市查找对应的县数据，更新列表
                    queryCountries();
                }
            }
        });

        //给返回按钮添加监听事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTRY){
                    queryCities();
                }else if (currentLevel == LEVEL_CITY){
                    queryProvinces();
                }
            }
        });

        //列表一开始展示的市所有省份的信息
        queryProvinces();

    }

    //查询全国的省，优先从数据库中查询，如果没有查询到就到服务器上查询
    private void queryProvinces() {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);//展示省份的列表没有返回按钮，故这里将其隐藏
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size()>0){
            dataList.clear();//清空原有数据
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        }else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address, "province");
        }
    }

    //查询省内的市，优先从数据库中查询，如果没有查询到就到服务器上查询
    private void queryCities() {
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList = DataSupport.where("provinceid=?",String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size()>0){
            dataList.clear();
            for (City city : cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        }else {
            int provinceCode = selectedProvince.getProvinceCode();
            String address = "http://guolin.tech/api/china/" + provinceCode;
            queryFromServer(address, "city");
        }
    }

    //查询市内的县，优先从数据库中查询，如果没有查询到就到服务器上查询
    private void queryCountries() {
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countryList = DataSupport.where("cityid=?", String.valueOf(selectedCity.getId())).find(Country.class);
        if (countryList.size()>0){
            dataList.clear();
            for (Country country : countryList){
                dataList.add(country.getCountryName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTRY;
        }else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = "http://guolin.tech/api/china/" + provinceCode + "/" + cityCode;
            queryFromServer(address, "country");
        }

    }

    //根据传入的地址从服务器上查询数据
    private void queryFromServer(String address, final String type) {
        showProgressDialog();//显示进度对话框
        //向服务器发送请求
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //回到主线程处理逻辑
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();//关闭进度对话框
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获得服务器返回的响应数据
                String responseText = response.body().string();
                boolean result = false;
                if ("province".equals(type)){
                    result = Utility.handleProvinceResponse(responseText);
                }else if ("city".equals(type)){
                    result = Utility.handleCityResponse(responseText, selectedProvince.getId());
                }else if ("country".equals(type)){
                    result = Utility.handleCountryResponse(responseText, selectedCity.getId());
                }
                if (result){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();//关闭进度对话框
                            if ("province".equals(type)){
                                queryProvinces();
                            }else if ("city".equals(type)){
                                queryCities();
                            }else if ("country".equals(type)){
                                queryCountries();
                            }
                        }
                    });
                }
            }
        });
    }

    private void showProgressDialog() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载......");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }


}
