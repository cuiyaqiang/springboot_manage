package com.guye.sun;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by suneee on 2018/8/29.
 */
public class Traffic {

    /**
     * 在尽量保证红绿灯时长为1:1的情况下去变换每节道路的宽度，如果宽度达到限制，再去调节红绿灯时长
     * 绿灯最长时间为120s，最短时间为20s
     * @param args
     */
    public static void main(String[] args) {
        //假设1横2纵,假设一个车道的情况下一个单位的车流长度为50m，道路变宽，车流长度按照比例减短
        int x = 1,y = 2;
        //接收各个车道的车流，封装成数组结构
        int a = 1,b = 3,c = 5;
        int[] x_flow = {a};
        int[] y_flow = {b,c};
        int[] x_lanes = new int[x_flow.length];
        int[] y_lanes = new int[y_flow.length];
        int[] flow = ArrayUtils.addAll(x_flow,y_flow);
        int[] lanes = countLans(flow);
        System.arraycopy(lanes,0,x_lanes,0,x_lanes.length);
        System.arraycopy(lanes,x_lanes.length,y_lanes,0,y_lanes.length);

        List<Road> roadList = new ArrayList<>();
        //初始化x道路/车流/车道
//        int lanes_x = 2;
        for (int i = 1;i <= x;i++){
            Road road = new Road(x_flow[i-1],"x",String.valueOf(i),x_lanes[i]);
            //初始化整段x道路的路口
            List<Cross> crosses = new ArrayList<>();
            for (int j = 1;j <= y;j++){
                Cross cross = new Cross("x",road.getOrder()+j);
                //初始化红绿灯，设置红绿灯的时长周期
                Light light = new Light("x",road.getOrder()+j);

                int greentime = (int)((road.getFlow_length() + cross.getCross_length())/road.getSpeed());
                light.setGreentime(greentime);
//                light.setSecond(time);
                cross.setLight(light);
                crosses.add(cross);
            }
            //初始化整段x道路的分段路
            List<RoadSelection> roadSelections = new ArrayList<>();
//            double total_length = 0;    //除去最后一段路的路长
            for (int j = 1;j <= y+1;j++){
                RoadSelection roadSelection = new RoadSelection(road.getOrder()+j);
                if (j == 1 || j == y+1){
                    roadSelection.setLanes(x_lanes[i-1]);
                }else {
                    int lanes_selection = x_flow[i-1]*y_lanes[i]/y_flow[i];
                    if (lanes_selection > 5){
                        lanes_selection = 5;
                    }
                    if (lanes_selection < 2){
                        lanes_selection = 2;
                    }
                    roadSelection.setLanes(lanes_selection);
                }
                roadSelections.add(roadSelection);
            }
            road.setRoadSelectionList(roadSelections);
            road.setCrossList(crosses);

            System.out.println(road.toString());
            roadList.add(road);
        }
        //初始化y道路
//        int lanes_y = 1;
        for (int i = 1;i <= y;i++){
            Road road = new Road(y_flow[i-1],"y",String.valueOf(i),y_lanes[i]);
            //初始化整段y道路的路口
            List<Cross> crosses = new ArrayList<>();
            for (int j = 1;j <= x;j++){
                Cross cross = new Cross("y",road.getOrder()+j);
                //初始化红绿灯，设置红绿灯的时长周期
                Light light = new Light("y",road.getOrder()+j);
                int time = (int)((road.getFlow_length() + cross.getCross_length())/road.getSpeed());
                light.setSecond(time);
                cross.setLight(light);
                crosses.add(cross);
            }
            //初始化整段道路的分段路
            List<RoadSelection> roadSelections = new ArrayList<>();
            double total_length = 0;    //除去最后一段路的路长
            for (int j = 1;j <= x+1;j++){
                RoadSelection roadSelection = new RoadSelection(road.getOrder()+String.valueOf(j));
                for (Cross cross:crosses) {
                    if (roadSelection.getOrder().equals(cross.getOrder())){
                        total_length = total_length + (road.getSpeed()*cross.getLight().getSecond()*2)
                                + cross.getCross_length();
                        roadSelection.setLength(road.getSpeed()*cross.getLight().getSecond()*2);
                    }
                }
                if (roadSelection.getOrder().equals(road.getOrder()+(x+1))){
                    roadSelection.setLength(road.getLength() - total_length);
                }
                roadSelections.add(roadSelection);
            }
            road.setRoadSelectionList(roadSelections);
            road.setCrossList(crosses);

            System.out.println(road.toString());
            roadList.add(road);
        }

        System.out.println(roadList);

    }

    //根据车流计算各个车道的路宽
    public static int[] countLans(int[] flow){
        int max_lanes = 5,min_lanes = 2;
        int[] lanes = new int[flow.length];
        int max_index = 0;  //车流数组中最大值的下标
        int min_index = 0;  //车流数组中最大值的下标
        int min_flow = flow[0];//定义最小值为该数组的第一个数
        for (int x = 0;x < flow.length;x++){
            if(min_flow > flow[x]){
                min_flow = flow[x];
                min_index = x;
            }
        }
        //封装车道的数组，假设最小车道数是2，最大车道数是5
        lanes[min_index] = 2;
        for (int y = 0;y < lanes.length;y++){
            if (y != min_index){
                lanes[y] = (int)(lanes[min_index]*flow[y]/flow[min_index]);
                if (lanes[y] > 5){
                    lanes[y] = 5;
                }
            }
        }

        for (int p = 0;p < flow.length;p++){
            System.out.println(flow[p]);
        }
        for (int p = 0;p < lanes.length;p++){
            System.out.println(lanes[p]);
        }

        return lanes;

    }

    @Test
    public void test(){
        int[] a = {1,2};
        int[] b = {3,4,5};
        int[] c = ArrayUtils.addAll(a,b);
        int[] e = new int[a.length];
        int[] f = new int[b.length];
        System.arraycopy(c,0,e,0,e.length);
        System.arraycopy(c,e.length,f,0,f.length);
//        for (int i = 0;i < e.length;i++){
//            System.out.println(e[i]);
//        }
        for (int i = 0;i < f.length;i++){
            System.out.println(f[i]);
        }

//        for (int i = 0;i < c.length;i++){
//            System.out.println(c[i]);
//        }

//        int[] d = Traffic.countLans(c);
//        for (int i = 0;i < d.length;i++){
//            System.out.println(d[i]);
//        }
    }
}
