package org.gec.smart.bean;

import java.util.Date;

public class AirConditionerLog {

    private String id; //编号
    private static Boolean oxygen = false; // 增氧
    private static Boolean humidification = false; // 加湿
    private static Boolean heating = false; // 加热
    private static Boolean aeration = false; // 抽风
    private static Boolean sleeping = false; // 睡眠
    private static Integer timer = 0; // 定时
    private static Boolean freshness = false; // 保鲜
    private static Integer temperature = 25; // 温度
    private static Integer deviceNo = 1; // 设备编号，默认为1
    private static Date createtime; //操作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Boolean getOxygen() {
        return oxygen;
    }

    public static void setOxygen(Boolean oxygen) {
        AirConditionerLog.oxygen = oxygen;
    }

    public static Boolean getHumidification() {
        return humidification;
    }

    public static void setHumidification(Boolean humidification) {
        AirConditionerLog.humidification = humidification;
    }

    public static Boolean getHeating() {
        return heating;
    }

    public static void setHeating(Boolean heating) {
        AirConditionerLog.heating = heating;
    }

    public static Boolean getAeration() {
        return aeration;
    }

    public static void setAeration(Boolean aeration) {
        AirConditionerLog.aeration = aeration;
    }

    public static Boolean getSleeping() {
        return sleeping;
    }

    public static void setSleeping(Boolean sleeping) {
        AirConditionerLog.sleeping = sleeping;
    }

    public static Integer getTimer() {
        return timer;
    }

    public static void setTimer(Integer timer) {
        AirConditionerLog.timer = timer;
    }

    public static Boolean getFreshness() {
        return freshness;
    }

    public static void setFreshness(Boolean freshness) {
        AirConditionerLog.freshness = freshness;
    }

    public static Integer getTemperature() {
        return temperature;
    }

    public static void setTemperature(Integer temperature) {
        AirConditionerLog.temperature = temperature;
    }

    public static Integer getDeviceNo() {
        return deviceNo;
    }

    public static void setDeviceNo(Integer deviceNo) {
        AirConditionerLog.deviceNo = deviceNo;
    }

    public static Date getCreatetime() {
        return createtime;
    }

    public static void setCreatetime(Date createtime) {
        AirConditionerLog.createtime = createtime;
    }

}
