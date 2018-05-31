package com.test;

import java.awt.*;

/**
 * Created by admin on 2017/7/19.
 */
public class TV {
    private Long id;
    private boolean flag;
    private Long id2;

    public TV() {
    }

    public TV(Long id, boolean flag) {
        this.id = id;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "" + id + "," + flag;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Long getId2() {
        return id2;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }
}
