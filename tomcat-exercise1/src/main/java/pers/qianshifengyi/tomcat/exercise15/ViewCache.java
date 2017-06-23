package pers.qianshifengyi.tomcat.exercise15;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshan on 17/6/12.
 */
public class ViewCache {

    private List<Area> areaList = new ArrayList<Area>();

    public List<Area> getAreaList() {
        return areaList;
    }
    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    // 供Digester调用的方法
    public void addArea(Area area) {
        this.areaList.add(area);
    }
}
