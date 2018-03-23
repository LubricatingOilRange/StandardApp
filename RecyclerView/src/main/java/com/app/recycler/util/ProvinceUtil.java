package com.app.recycler.util;

import com.app.recycler.R;
import com.app.recycler.entry.City;
import com.app.recycler.entry.Province;

import java.util.ArrayList;
import java.util.List;

public class ProvinceUtil {
    /*
     * 获取省市
     */
    public static List<Province> getProvinceList() {
        List<Province> provinceList = new ArrayList<>();

        List<City> dataList0 = new ArrayList<>();
        final String FU_JIAN = "福建省";
        final int FU_JIAN_ICON = R.mipmap.ic_launcher;
        dataList0.add(new City("福州", FU_JIAN, FU_JIAN_ICON));
        dataList0.add(new City("厦门", FU_JIAN, FU_JIAN_ICON));
        dataList0.add(new City("泉州", FU_JIAN, FU_JIAN_ICON));
        dataList0.add(new City("宁德", FU_JIAN, FU_JIAN_ICON));
        dataList0.add(new City("漳州", FU_JIAN, FU_JIAN_ICON));
        provinceList.add(new Province(FU_JIAN,dataList0,FU_JIAN_ICON));

        List<City> dataList1 = new ArrayList<>();
        final String AN_HUI = "安徽省";
        final int AN_HUI_ICON = R.mipmap.ic_launcher;
        dataList1.add(new City("合肥", AN_HUI, AN_HUI_ICON));
        dataList1.add(new City("芜湖", AN_HUI, AN_HUI_ICON));
        dataList1.add(new City("蚌埠", AN_HUI, AN_HUI_ICON));
        provinceList.add(new Province(AN_HUI,dataList1,AN_HUI_ICON));

        List<City> dataList2 = new ArrayList<>();
        final String AN_HUI_2 = "安徽省-2";
        final int AN_HUI_ICON_2 = R.mipmap.ic_launcher;
        dataList2.add(new City("合肥-2", AN_HUI_2, AN_HUI_ICON_2));
        provinceList.add(new Province(AN_HUI_2,dataList2,AN_HUI_ICON_2));

        List<City> dataList3 = new ArrayList<>();
        final String ZHE_JIANG = "浙江省";
        final int ZHE_JIANG_ICON = R.mipmap.ic_launcher;
        dataList3.add(new City("杭州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("宁波", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("温州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("嘉兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("绍兴", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("金华", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("湖州", ZHE_JIANG, ZHE_JIANG_ICON));
        dataList3.add(new City("舟山", ZHE_JIANG, ZHE_JIANG_ICON));
        provinceList.add(new Province(ZHE_JIANG,dataList3,ZHE_JIANG_ICON));

        List<City> dataList4 = new ArrayList<>();
        final String JIANG_SU = "江苏省";
        final int JIANG_SU_ICOM = R.mipmap.ic_launcher;
        dataList4.add(new City("南京", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("苏州", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("徐州", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("南通", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("无锡", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("盐城", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("淮安", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("泰州", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("常州", JIANG_SU, JIANG_SU_ICOM));
        dataList4.add(new City("连云港", JIANG_SU, JIANG_SU_ICOM));
        provinceList.add(new Province(JIANG_SU,dataList4,JIANG_SU_ICOM));
        return provinceList;
    }
}
