package org.gec.model;

import java.util.Date;

public class LightLog {

    private String id;
    private boolean operation; //开关
    private Date createtime; //操作时间
    private int deviceno; //设备编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(int deviceno) {
        this.deviceno = deviceno;
    }

    @Override
    public String toString() {
        return "LightLog{" +
                "id='" + id + '\'' +
                ", operation=" + operation +
                ", createtime='" + createtime + '\'' +
                ", deviceno=" + deviceno +
                '}';
    }
}
