package org.gec.model;

import java.util.Date;

public class Environment {

    private String id;
    private boolean body; //有人
    private float humidity; //湿度
    private boolean fire;  //火焰
    private int smoke;  //烟雾
    private float temperature; //温度
    private int illuminance; //灯光
    private Date createtime; //检测时间

    @Override
    public String toString() {
        return "Environment{" +
                "id='" + id + '\'' +
                ", body=" + body +
                ", humidity=" + humidity +
                ", fire=" + fire +
                ", smoke=" + smoke +
                ", temperature=" + temperature +
                ", illuminance=" + illuminance +
                ", createtime='" + createtime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBody() {
        return body;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public int getSmoke() {
        return smoke;
    }

    public void setSmoke(int smoke) {
        this.smoke = smoke;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(int illuminance) {
        this.illuminance = illuminance;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
