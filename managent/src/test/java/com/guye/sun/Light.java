package com.guye.sun;

/**
 * Created by suneee on 2018/8/14.
 */
public class Light {
    //状态
//    private String state;
    //时长
    private int second;
    //红灯时长
    private int redtime;
    //绿灯时长
    private int greentime;
    //横纵标识
    private String axis;
    //序号
    private String order;

    public Light() {
    }

    public Light(String axis, String order) {
        this.axis = axis;
        this.order = order;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
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

    public int getRedtime() {
        return redtime;
    }

    public void setRedtime(int redtime) {
        this.redtime = redtime;
    }

    public int getGreentime() {
        return greentime;
    }

    public void setGreentime(int greentime) {
        this.greentime = greentime;
    }

    @Override
    public String toString() {
        return "Light{" +
                "second=" + second +
                ", redtime=" + redtime +
                ", greentime=" + greentime +
                ", axis='" + axis + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
