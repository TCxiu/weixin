package com.xiu.wserver.model;

import java.io.Serializable;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/9 12:59
 * @Description 类描述:
 */

public class Tag implements Serializable {
    private Integer id;

    private String name;

    private Long count;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
