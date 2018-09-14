package com.guye.sun;

import java.util.List;

/**
 * Created by suneee on 2018/8/29.
 */
public class Road {
    //路名
    private String name;
    //路长
    private double length = 2000;
    //车道数
    private int lanes;
    //车速
    private double speed = 11.11;
    //车流量占比
    private int flow;
    //道路横纵标识
    private String axis;
    //道路序号
    private String order;
    //车流长度
    private double flow_length;
    //包含路段
    private List<RoadSelection> roadSelectionList;

    public List<Cross> getCrossList() {
        return crossList;
    }

    public void setCrossList(List<Cross> crossList) {
        this.crossList = crossList;
    }

    //包含路口
    private List<Cross> crossList;

    public Road() {
    }

    public Road(int flow, String axis, String order, int lanes) {
        this.name = axis + "_" + order;
        this.flow_length = 200*flow/lanes;  //200代表一个单位的车流在一个车道的长度
        this.flow = flow;
        this.axis = axis;
        this.order = order;
        this.lanes = lanes;
    }

    public double getFlow_length() {
        return flow_length;
    }

    public void setFlow_length(double flow_length) {
        this.flow_length = flow_length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int lanes) {
        this.lanes = lanes;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public String getAxis() {
        return axis;
    }

    public void setAxis(String axis) {
        this.axis = axis;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<RoadSelection> getRoadSelectionList() {
        return roadSelectionList;
    }

    public void setRoadSelectionList(List<RoadSelection> roadSelectionList) {
        this.roadSelectionList = roadSelectionList;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", lanes=" + lanes +
                ", speed=" + speed +
                ", flow=" + flow +
                ", axis='" + axis + '\'' +
                ", order='" + order + '\'' +
                ", flow_length=" + flow_length +
                ", roadSelectionList=" + roadSelectionList +
                ", crossList=" + crossList +
                '}';
    }
}
