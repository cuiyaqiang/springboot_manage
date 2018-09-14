package com.guye.sun;

/**
 * Created by suneee on 2018/8/14.
 */
public class RoadSelection {
    //路名
//    private String name;

    //车速
//    private double speed;
    //车流量占比
//    private int flow;
    //道路横纵标识
//    private String axis;
    //车流长度
    private double flow_length;
    //车道数
    private int lanes;
    //路长
    private double length;
    //道路序号
    private String order;

    public RoadSelection() {
    }

    public RoadSelection(String order) {
        this.order = order;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int lanes) {
        this.lanes = lanes;
    }

    public double getFlow_length() {
        return flow_length;
    }

    public void setFlow_length(double flow_length) {
        this.flow_length = flow_length;
    }

    @Override
    public String toString() {
        return "RoadSelection{" +
                "flow_length=" + flow_length +
                ", lanes=" + lanes +
                ", length=" + length +
                ", order='" + order + '\'' +
                '}';
    }
}
