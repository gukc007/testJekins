package com.test.cal;

/**
 * Created by admin on 2018/3/22.
 */
public class JsonTest {

    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public static class Info{
        private String ep;

        public String getEp() {
            return ep;
        }

        public void setEp(String ep) {
            this.ep = ep;
        }
    }
}
