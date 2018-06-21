package com.xiu.wserver.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/8 14:26
 * @Description 类描述:
 */

public class Menu implements Serializable {
    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
