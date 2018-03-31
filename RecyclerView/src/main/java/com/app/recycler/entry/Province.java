package com.app.recycler.entry;

import java.util.List;

public class Province {
    private String provinceName;

    private List<City> cityList;

    private int icon;

    public Province(String provinceName, List<City> cityList, int icon) {
        this.provinceName = provinceName;
        this.cityList = cityList;
        this.icon = icon;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
