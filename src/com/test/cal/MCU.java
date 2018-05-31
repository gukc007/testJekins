package com.test.cal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/4.
 */
public class MCU {
    public int id;

    public ResourcePool resourcePool;

    public MCU parentMCU;

    public List<MCU> childrenMCU = new ArrayList<>();

    public List<ConfSite> confSites = new ArrayList<>();

    public int remainConfSite;

    public MCU() {
    }

    public MCU(int id, int remainConfSite) {
        this.id = id;
        this.remainConfSite = remainConfSite;
    }
}
