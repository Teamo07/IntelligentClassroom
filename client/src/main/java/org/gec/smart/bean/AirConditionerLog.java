package org.gec.smart.bean;

import java.util.Date;

public class AirConditionerLog {

    private String id; //编号
    private  Boolean oxygen = false; // 增氧
    private  Boolean humidification = false; // 加湿
    private  Boolean heating = false; // 加热
    private  Boolean aeration = false; // 抽风
    private  Boolean sleeping = false; // 睡眠
    private  Integer timer = 0; // 定时
    private  Boolean freshness = false; // 保鲜
    private  Integer temperature = 25; // 温度
    private  Integer deviceNo = 1; // 设备编号，默认为1
    private  Date createtime; //操作时间、

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOxygen() {
        return oxygen;
    }

    public void setOxygen(Boolean oxygen) {
        this.oxygen = oxygen;
    }

    public Boolean getHumidification() {
        return humidification;
    }

    public void setHumidification(Boolean humidification) {
        this.humidification = humidification;
    }

    public Boolean getHeating() {
        return heating;
    }

    public void setHeating(Boolean heating) {
        this.heating = heating;
    }

    public Boolean getAeration() {
        return aeration;
    }

    public void setAeration(Boolean aeration) {
        this.aeration = aeration;
    }

    public Boolean getSleeping() {
        return sleeping;
    }

    public void setSleeping(Boolean sleeping) {
        this.sleeping = sleeping;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public Boolean getFreshness() {
        return freshness;
    }

    public void setFreshness(Boolean freshness) {
        this.freshness = freshness;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Integer deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
