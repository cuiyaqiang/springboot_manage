package com.guye.sun.redis;

import java.io.Serializable;

/**
 * Created by suneee on 2018/9/26.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1530813282496676263L;
    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
