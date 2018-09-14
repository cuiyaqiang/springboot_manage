package com.guye.sun;

/**
 * Created by suneee on 2018/8/15.
 */
public class TrafficTest {

//    public static RoadSelection x_road_11 = new RoadSelection(4.00,"横",11);
//    public static RoadSelection x_road_12 = new RoadSelection(4.00,"横",12);
//    public static RoadSelection x_road_13 = new RoadSelection(4.00,"横",13);
//    public static RoadSelection y_road_11 = new RoadSelection(4.00,"纵",11);
//    public static RoadSelection y_road_12 = new RoadSelection(4.00,"纵",12);
//    public static RoadSelection y_road_21 = new RoadSelection(4.00,"纵",21);
//    public static RoadSelection y_road_22 = new RoadSelection(4.00,"纵",22);
//    public static Light x_light_11 = new Light(30,"横",11);
//    public static Light x_light_12 = new Light(30,"横",12);
//    public static Light y_light_11 = new Light(30,"横",11);
//    public static Light y_light_21 = new Light(30,"横",21);
//    public static Cross x_cross_11 = new Cross(10,"横",11);
//    public static Cross x_cross_12 = new Cross(10,"横",12);
//    public static Cross y_cross_11 = new Cross(10,"纵",11);
//    public static Cross y_cross_21 = new Cross(10,"纵",21);
//
//    public static void main(String[] args) {
//
//        x_road_11.setLanes(2);
//        y_road_11 = TrafficTest.countRoad_y("3:2",x_road_11,y_road_11);
//        x_road_12 = TrafficTest.countRoad_x_next(x_road_11,"5:3",x_road_12,x_light_11);
//        x_cross_11.setPreRoad(x_road_11);
//        x_cross_11.setNextRoad(x_road_12);
//        x_cross_11.setLights(x_light_11);
//
//        y_road_21 = TrafficTest.countRoad_y("5:3",x_road_12,y_road_21);
//        x_road_13 = TrafficTest.countRoad_x_next(x_road_12,"4:2",x_road_13,x_light_12);
//        x_cross_12.setPreRoad(x_road_12);
//        x_cross_12.setNextRoad(x_road_13);
//        x_cross_12.setLights(x_light_12);
////        y_cross_11.setPreRoad(y_road_11);
//        System.out.println(y_road_11.toString());
//
//    }
//
//    //根据横向道路和车流比例计算纵向道路
//    public static RoadSelection countRoad_y(String carRatio, RoadSelection x_road, RoadSelection y_road){
//        String[] carRatioArr = carRatio.split(":");
//        int x_ratio = Integer.valueOf(carRatioArr[0]);
//        x_road.setFlow(x_ratio);
//        int y_ratio = Integer.valueOf(carRatioArr[1]);
//        y_road.setFlow(y_ratio);
//        int y_road_lanes = TrafficTest.countLane_y(x_ratio,y_ratio,x_road.getLanes());
//        y_road.setLanes(y_road_lanes);
//        return y_road;
//    }
//
//    //根据比例计算纵向车道数
//    public static int countLane_y(int x_ratio,int y_ratio,int x_road_lanes){
//        int y_road_lanes = 0;
//        double y_road_lane = (double) x_road_lanes * y_ratio / x_ratio;
//        if (y_road_lane < 1){
//            y_road_lanes = 1;
//        }else {
//            y_road_lanes = Integer.parseInt(String.valueOf(Math.round(y_road_lane)));
//        }
//        return y_road_lanes;
//    }
//
//    //根据上一条横向路和路口的车流比例计算下一条横向路的车道
//    public static RoadSelection countRoad_x_next(RoadSelection x_road_pre, String carRatio, RoadSelection x_road_next, Light x_light) {
//
//        String[] carRatioArr = carRatio.split(":");
//        int x_ratio = Integer.valueOf(carRatioArr[0]);
//        x_road_next.setFlow(x_ratio);
//        int lanes_x_12 = x_ratio * (x_road_pre.getLanes()) / (x_road_pre.getFlow());
//        x_road_next.setLanes(lanes_x_12);
//        x_road_next.setLength(x_road_next.getSpeed() * x_light.getSecond() * 4);
//        return x_road_next;
//    }
}
