package org.gec.model;

import java.util.Date;

public class AirConditionerLog {

    private String id;
    private boolean oxygen;   //增氧
    private boolean humidification; //加湿
    private boolean heating;  //加热
    private boolean aeration;  // 抽风
    private boolean sleeping;  //睡眠
    private int timer;   //定时
    private boolean freshness; //保鲜
    private int temperature; //温度
    private int deviceno;  //设备编号
    private Date createtime; //操作时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOxygen() {
        return oxygen;
    }

    public void setOxygen(boolean oxygen) {
        this.oxygen = oxygen;
    }

    public boolean isHumidification() {
        return humidification;
    }

    public void setHumidification(boolean humidification) {
        this.humidification = humidification;
    }

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public boolean isAeration() {
        return aeration;
    }

    public void setAeration(boolean aeration) {
        this.aeration = aeration;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isFreshness() {
        return freshness;
    }

    public void setFreshness(boolean freshness) {
        this.freshness = freshness;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(int deviceno) {
        this.deviceno = deviceno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "AirConditionerLog{" +
                "id='" + id + '\'' +
                ", oxygen=" + oxygen +
                ", humidification=" + humidification +
                ", heating=" + heating +
                ", aeration=" + aeration +
                ", sleeping=" + sleeping +
                ", timer=" + timer +
                ", freshness=" + freshness +
                ", temperature=" + temperature +
                ", deviceno=" + deviceno +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
