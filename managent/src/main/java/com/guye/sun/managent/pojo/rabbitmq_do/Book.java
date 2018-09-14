package com.guye.sun.managent.pojo.rabbitmq_do;

import java.io.Serializable;

/**
 * Created by suneee on 2018/6/1.
 */
public class Book implements Serializable {

    private static final long serialVersionUID = -2164058270260403154L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
