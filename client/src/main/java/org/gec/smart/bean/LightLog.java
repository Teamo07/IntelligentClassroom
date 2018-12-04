package org.gec.smart.bean;

import java.util.Date;

public class LightLog {

    private String id; //编号
    private Boolean operation; //操作指令
    private Date createTime; //操作时间
    private Integer deviceNo; //设备编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOperation() {
        return operation;
    }

    public void setOperation(Boolean operation) {
        this.operation = operation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Integer deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Override
    public String toString() {
        return "LightLog{" +
                "id='" + id + '\'' +
                ", operation=" + operation +
                ", createTime=" + createTime +
                ", deviceNo=" + deviceNo +
                '}';
    }
}
