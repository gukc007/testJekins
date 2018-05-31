package com.test.cal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/8/4.
 */
public class Conference {
    ResourcePool[] r;
    MCU[] m;
    ConfSite[] c;
    double confBandwidth;
    int maxDepth;

    public void distributeMCU() throws Exception {
        if (1 == 1) {
            throw new Exception();
        }
        init();
        List<MCU> needMcu = new ArrayList<>();
        confSiteDestribute(needMcu);

        List<MCU> beLinkMcu = new ArrayList<>();

        List<MCU> root = new ArrayList<>();
        root.add(new MCU());
        List<MCU> testMcus = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            MCU mcu = new MCU();
            mcu.id = i + 2;
            testMcus.add(mcu);
        }
        setMcuParents(testMcus, root, 0);
        System.out.println(count);
    }
    int count = 0;
    //假设mcus[0] 为主mcu;
    public List<MCU> getMcuParents(List<MCU> mcus, int deep) {

        List<MCU> parents = new ArrayList<>();
        for (MCU mcu : mcus) {
            if (findMcuDepth(mcu) == deep) {
                parents.add(mcu);
            }
        }
        return parents;
    }

    public List<MCU> getMcuWithoutParents(List<MCU> mcus) {
        return mcus.stream().filter(a -> a.parentMCU == null).collect(Collectors.toList());
    }

    public void setMcuParents(List<MCU> mcus, List<MCU> parents, int i) {
        if (parents.size() <= 0) {
            return;
        }
        int deep = findMcuDepth(parents.get(0));
        if (deep >= maxDepth) {
            return;
        }
        if (i >= mcus.size()) {
            if (mcus.stream().allMatch(a -> a.parentMCU != null)) {
                count ++;
            }else if (deep < maxDepth) {
                List<MCU> newParents = getMcuParents(mcus, deep + 1);
                List<MCU> mcuWithoutParents = getMcuWithoutParents(mcus);
                if (mcuWithoutParents.size() <= 0) {
                    return;
                }
                setMcuParents(getMcuWithoutParents(mcus), newParents, 0);
            }

            return;
        }
        MCU mcu = mcus.get(i);
        for (MCU parent : parents) {
            mcu.parentMCU = parent;
            setMcuParents(mcus, parents, i + 1);
            mcu.parentMCU = null;
        }
        setMcuParents(mcus, parents, i + 1);
    }

    public int findMcuDepth(MCU mcu) {
        if (mcu.parentMCU != null) {
            return findMcuDepth(mcu.parentMCU) + 1;
        }
        return 1;
    }

    public void confSiteDestribute(List<MCU> needMcu) {
        for (int i = 0; i < r.length; i++) {
            List<MCU> mcus = r[i].mcus;
            mcus.sort((m1, m2) -> {
                if (m1.remainConfSite >= m2.remainConfSite) {
                    return 1;
                } else {
                    return -1;
                }
            });
            int confSitesSize = r[i].confSites.size();
            chooseMcus.clear();

            calC(mcus, new ArrayList<>(), 0, confSitesSize);
            if (chooseMcus.size() <= 0) {
                System.out.println(r[i].id + "资源池资源不足");
                break;
            }

            int j = 0;
            for (MCU mcu : chooseMcus) {
                while (mcu.remainConfSite > 0 && j < r[i].confSites.size()) {
                    mcu.confSites.add(r[i].confSites.get(j));
                    mcu.remainConfSite--;
                    j++;
                }

            }

            needMcu.addAll(chooseMcus);
        }
    }

    List<MCU> chooseMcus = new ArrayList<>();

    //总mcu, 当前选择的mcu，下标， 剩余未分配会场数
    public void calC(List<MCU> mcus, List<MCU> selectMcus, int i, int confRemainSize) {

        for (int j = i; j < mcus.size(); j++) {
            MCU mcu = mcus.get(j);
            selectMcus.add(mcu);
            int size = confRemainSize - mcu.remainConfSite;
            if (mcu.remainConfSite >= confRemainSize) {
                if (chooseMcus.size() == 0 || chooseMcus.size() > selectMcus.size()
                        || chooseMcus.size() == selectMcus.size() && smallerMcuRemainSize(selectMcus)) {
                    chooseMcus.clear();
                    chooseMcus.addAll(selectMcus);
                }
                selectMcus.remove(mcu);
                break;
            }
            calC(mcus, selectMcus, j + 1, confRemainSize - mcu.remainConfSite);
            selectMcus.remove(mcu);
        }
    }

    public boolean smallerMcuRemainSize(List<MCU> selectMcus) {
        int selectMcuRemainSize = 0;
        int chooseMcuRemainSize = 0;
        for (MCU mcu : selectMcus) {
            selectMcuRemainSize += mcu.remainConfSite;
        }
        for (MCU mcu : chooseMcus) {
            chooseMcuRemainSize += mcu.remainConfSite;
        }
        if (selectMcuRemainSize < chooseMcuRemainSize) {
            return true;
        } else {
            return false;
        }
    }

    public void init() {

        maxDepth = 3;
        confBandwidth = 700;

        r = new ResourcePool[3];
        r[0] = new ResourcePool(1, 30 * 1000, 30 * 1000);
        r[1] = new ResourcePool(2, 40 * 1000, 40 * 1000);
        r[2] = new ResourcePool(3, 20 * 1000, 20 * 1000);

        m = new MCU[10];
        m[0] = new MCU(1, 7);
        m[1] = new MCU(2, 4);
        m[2] = new MCU(3, 3);
        m[3] = new MCU(4, 5);
        m[4] = new MCU(5, 3);
        m[5] = new MCU(6, 2);
        m[6] = new MCU(7, 2);
        m[7] = new MCU(8, 3);
        m[8] = new MCU(9, 4);
        m[9] = new MCU(10, 6);


        c = new ConfSite[22];
        for (int i = 0; i < c.length; i++) {
            c[i] = new ConfSite(i);
        }

        for (int i = 0; i < 7; i++) {
            r[1].confSites.add(c[i]);
            c[i].resourcePool = r[1];
        }
        for (int i = 7; i < 15; i++) {
            r[0].confSites.add(c[i]);
            c[i].resourcePool = r[0];
        }
        for (int i = 15; i < c.length; i++) {
            r[2].confSites.add(c[i]);
            c[i].resourcePool = r[2];
        }

        for (int i = 0; i < 3; i++) {
            r[0].mcus.add(m[i]);
            m[i].resourcePool = r[0];
        }
        for (int i = 3; i < 6; i++) {
            r[1].mcus.add(m[i]);
            m[i].resourcePool = r[1];
        }
        for (int i = 6; i < m.length; i++) {
            r[2].mcus.add(m[i]);
            m[i].resourcePool = r[2];
        }
    }
}
