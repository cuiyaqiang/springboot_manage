package com.guye.sun.redis;

import ch.qos.logback.core.util.CloseUtil;
import com.guye.sun.common.utils.*;
import com.guye.sun.common.utils.serialization.ListTranscoder;
import com.guye.sun.common.utils.serialization.ObjectsTranscoder;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suneee on 2018/9/26.
 */
public class RedisTest {

//    private Jedis jedis;
//    @Before
//    public void before() {
//        jedis = new Jedis("127.0.0.1");
//    }
//
//    /**
//     * 测试List数据结构的操作
//     */
//    @Test
//    public void test1(){
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            User user = new User();
//            user.setId(i);
//            user.setName("guye_"+i);
//            list.add(user);
//        }
//
//        jedis.set("userList".getBytes(), SerializationUtil.serialize(list));
//        List<User> userList = (List<User>) SerializationUtil.deserialize(jedis.get("userList".getBytes()));
//        for (User user : userList){
//            System.out.println(user.getId()+"---"+user.getName());
//        }
//        System.out.println(userList);
//    }
//
//    /**
//     * 测试map数据结构的操作
//     */
//    @Test
//    public void test2(){
//        Map<String,List<User>> map = new HashMap<>();
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            User user = new User();
//            user.setId(i);
//            user.setName("aaa_"+i);
//            list.add(user);
//        }
//        map.put("userList",list);
//        jedis.set("userList".getBytes(), SerializationUtil.serialize(map));
//        Map<String,List<User>> userMap = (Map<String, List<User>>) SerializationUtil.deserialize(jedis.get("userList".getBytes()));
//        List<User> userList = userMap.get("userList");
//        for (User user : userMap.get("userList")){
//            System.out.println(user.getId()+"---"+user.getName());
//        }
//        System.out.println(userList);
//    }
//
//    /**
//     * 测试List中包含map数据结构的操作
//     */
//    @Test
//    public void test3(){
//        List<Map<String,List<User>>> userList = new ArrayList<>();
//        List<User> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            User user = new User();
//            user.setId(i);
//            user.setName("bbb "+i);
//            list.add(user);
//        }
//        for (int i = 0; i < 3; i++){
//            Map<String,List<User>> map = new HashMap<>();
//            map.put("map_"+i,list);
//            userList.add(map);
//        }
//        jedis.set("userList".getBytes(), SerializationUtil.serialize(userList));
//        List<Map<String,List<User>>> userMapList = (List<Map<String, List<User>>>) SerializationUtil.deserialize(jedis.get("userList".getBytes()));
//        for (Map map1 : userMapList){
//            for (Object key : map1.keySet()){
//                List<User> uList = (List<User>) map1.get(key);
//                System.out.println(uList);
//                System.out.println("-----------------------");
//            }
//        }
//    }


}
