package com.guye.sun;

/**
 * Created by suneee on 2018/8/14.
 */
//路口
public class Cross {
    //主键
//    private int id;
    //前段马路
//    private RoadSelection preRoad;
    //后端马路
//    private RoadSelection nextRoad;
    //路口车流基数
//    private int x_base;
    //路口横纵标识
    private String axis;
    //交通灯
    private Light light;
    //路口长度
    private double cross_length = 15.0;
    //路口序号
    private String order;

    public Cross() {
    }

    public Cross(String axis, String order) {
        this.axis = axis;
        this.order = order;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light lights) {
        this.light = lights;
    }

    public double getCross_length() {
        return cross_length;
    }

    public void setCross_length(double cross_length) {
        this.cross_length = cross_length;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Cross{" +
                "axis='" + axis + '\'' +
                ", light=" + light +
                ", cross_length=" + cross_length +
                ", order='" + order + '\'' +
                '}';
    }
}
