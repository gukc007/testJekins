package com.test.cal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/4.
 */
public class ResourcePool {
    public int id;
    public double upLoad;
    public double downLoad;
    public List<MCU> mcus = new ArrayList<>();
    public List<ConfSite> confSites = new ArrayList<>();

    public ResourcePool() {
    }

    public ResourcePool(int id, double upLoad, double downLoad) {
        this.id = id;
        this.upLoad = upLoad;
        this.downLoad = downLoad;
    }
}
